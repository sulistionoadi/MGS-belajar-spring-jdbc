package mgs.training.springboot.belajarjdbc.service.impljpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mgs.training.springboot.belajarjdbc.constant.ErrorCode;
import mgs.training.springboot.belajarjdbc.dao.PenggunaDao;
import mgs.training.springboot.belajarjdbc.dao.SessionLoginDao;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.dto.login.LoginDto;
import mgs.training.springboot.belajarjdbc.dto.login.UserAuthorityDto;
import mgs.training.springboot.belajarjdbc.entity.MenuEntity;
import mgs.training.springboot.belajarjdbc.entity.PenggunaEntity;
import mgs.training.springboot.belajarjdbc.entity.SessionLogin;
import mgs.training.springboot.belajarjdbc.service.AppCacheService;
import mgs.training.springboot.belajarjdbc.service.LoginService;
import mgs.training.springboot.belajarjdbc.util.AuthUtil;
import mgs.training.springboot.belajarjdbc.util.JwtTokenUtil;

@Service("loginJpaService")
@Slf4j
public class LoginServiceJpaImpl implements LoginService {

	@Value("${jwt.expiration:360}")
	private Long expiration;
	
	private final SessionLoginDao dao;
	private final PenggunaDao penggunaDao;
	private final PasswordEncoder encoder;
	private final JwtTokenUtil jwtUtil;
	private final AppCacheService cacheService;
	
	public LoginServiceJpaImpl(SessionLoginDao dao, PenggunaDao penggunaDao, PasswordEncoder encoder, JwtTokenUtil jwtUtil, 
			AppCacheService cacheService) {
		this.dao = dao;
		this.penggunaDao = penggunaDao;
		this.encoder = encoder;
		this.jwtUtil = jwtUtil;
		this.cacheService = cacheService;
	}

	@Override
	@Transactional
	public HttpRespModel login(LoginDto dto) {
		// TODO Auto-generated method stub
		Optional<PenggunaEntity> op = penggunaDao.findByUsername(dto.getUsername());
		if(op.isEmpty()) 
			return HttpRespModel.error(ErrorCode.UNAUTHENTICATED, "Username/password tidak sesuai");
		
		PenggunaEntity pengguna = op.get();
		if(!encoder.matches(dto.getPassword(), pengguna.getPassword())) {
			return HttpRespModel.error(ErrorCode.UNAUTHENTICATED, "Username/password tidak sesuai");
		}
		
		dao.deleteByUserId(pengguna.getId());
		
		SessionLogin session = SessionLogin.builder()
				.userId(pengguna.getId())
				.createdAt(new Date())
				.build();
		dao.save(session);

		LoginDto profile = LoginDto.builder().sessionId(session.getId()).username(dto.getUsername())
				.createdAt(new Date())
				.expiredAt(new Date(System.currentTimeMillis() + expiration * 1000))
				.build();
		String token = jwtUtil.generateToken(profile);
		profile.setToken(token);

		session.setToken(token);
		dao.save(session);
		
		UserAuthorityDto authorityDto = setAuthoritiesAndPermittedMenu(pengguna);
		cacheService.putGroupPrivilege(pengguna.getUsername(), authorityDto);
		return HttpRespModel.ok(profile);
	}
	
	private UserAuthorityDto setAuthoritiesAndPermittedMenu(PenggunaEntity pengguna) {
		List<String> listAuthorities = new ArrayList<>();
		List<String> permittedAction = new ArrayList<>();
		Queue<MenuEntity> dequeMenu = new LinkedList<>();
		Map<Long, MenuEntity> mapMenu = new HashMap<>();

		log.debug("User:{} ", pengguna.getUsername());
		log.debug("has {} permittedMenu", pengguna.getJabatan().getMenuSet().size());
        pengguna.getJabatan().getMenuSet().stream().filter(MenuEntity::getActive).collect(Collectors.toSet()).
                forEach(menu -> {
                	listAuthorities.add(menu.getMenuLink());
        });

		UserAuthorityDto authorityDto = AuthUtil.buildObjectUserAuthority(listAuthorities);
		authorityDto.setUserId(pengguna.getId());
		authorityDto.setUsername(pengguna.getUsername());
		return authorityDto;
	}

	@Override
	public HttpRespModel logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean validateToken(Long sessionId, String token) {
		Optional<SessionLogin> op = dao.findById(sessionId);
		if(op.isEmpty()) {
			log.warn("Invalid Token");
			return false;
		}
		
		SessionLogin session = op.get();
		if(!token.equals(session.getToken())) {
			log.warn("Invalid Token");
			return false;
		}
		
		return true;
	}

}

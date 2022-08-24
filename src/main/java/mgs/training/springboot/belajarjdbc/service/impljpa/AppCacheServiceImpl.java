package mgs.training.springboot.belajarjdbc.service.impljpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mgs.training.springboot.belajarjdbc.dao.PenggunaDao;
import mgs.training.springboot.belajarjdbc.dto.login.UserAuthorityDto;
import mgs.training.springboot.belajarjdbc.entity.MenuEntity;
import mgs.training.springboot.belajarjdbc.entity.PenggunaEntity;
import mgs.training.springboot.belajarjdbc.service.AppCacheService;
import mgs.training.springboot.belajarjdbc.util.AuthUtil;

@Service
@Slf4j
public class AppCacheServiceImpl implements AppCacheService {

	private final PenggunaDao dao;
	
	public AppCacheServiceImpl(PenggunaDao dao) {
		this.dao = dao;
	}

	@Override
	@Cacheable(value = "user-authorities", key = "#username")
	public UserAuthorityDto getGroupPrivilege(String username){
		Optional<PenggunaEntity> op = dao.findByUsername(username);
		if(op.isEmpty()) {
			log.warn("USER-AUTHORITY-CACHE get data pengguna dengan id:{} tidak ditemukan", username);
			return null;
		}
		
		PenggunaEntity pengguna = op.get();
		
		
		List<String> listAuthority = pengguna.getJabatan().getMenuSet().stream()
				.map(MenuEntity::getMenuLink)
				.collect(Collectors.toList());
		return AuthUtil.buildObjectUserAuthority(listAuthority);
	}
	
	@Override
	@CachePut(value = "user-authorities", key = "#username")
	public UserAuthorityDto putGroupPrivilege(String username, UserAuthorityDto authorityObj){
		return authorityObj;
	}
	
}

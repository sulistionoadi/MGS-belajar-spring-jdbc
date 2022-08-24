package mgs.training.springboot.belajarjdbc.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import mgs.training.springboot.belajarjdbc.constant.ErrorCode;
import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.LoginDto;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -3301605591108950415L;

	private static final String CLAIM_KEY_USERNAME = "name";

	private final ObjectMapper objectMapper;
	
	@Value("${jwt.secret:the$ecret}")
	private String secret;
	
	@Value("${jwt.expiration:360}")
	private Long expiration;
	
	@Autowired
	public JwtTokenUtil(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public String getUsernameFromToken(String token) throws Exception {
		final Claims claims = getClaimsFromToken(token);
		return claims.get(CLAIM_KEY_USERNAME, String.class);
	}

	public Date getCreatedDateFromToken(String token) throws Exception {
		final Claims claims = getClaimsFromToken(token);
		return claims.getIssuedAt();
	}

	public Date getExpirationDateFromToken(String token) throws Exception {
		final Claims claims = getClaimsFromToken(token);
		return claims.getExpiration();
	}

	public Claims getClaimsFromToken(String token) throws Exception {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	private Date generateExpirationDate() {
		if(expiration < 1) {
			return null;
		} else {
			return new Date(System.currentTimeMillis() + expiration * 1000);
		}
	}

	private Boolean isTokenExpired(String token) throws Exception {
		final Date expiration = getExpirationDateFromToken(token);
		if(expiration==null) return false;
		
		//log.debug("Token expiredAt:{}, Is Token Expired ? {}", expiration, expiration.before(new Date()));
		return expiration.before(new Date());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		//log.debug("Issued At:{} lastPasswordReset:{} is before:{}", created, lastPasswordReset, created.before(lastPasswordReset));
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public String generateToken(LoginDto user) {
		try {
			Map<String, Object> claims = new HashMap<>();
			claims.put(Claims.SUBJECT, objectMapper.writeValueAsString(user));
			claims.put(Claims.ISSUED_AT, new Date().getTime()/1000);
			claims.put(CLAIM_KEY_USERNAME, user.getUsername());
			return generateToken(claims);
		} catch (JsonProcessingException e) {
			throw new CustomException(ErrorCode.ERROR_OTHER, "Failed to encode JSON");
		}
	}

	private String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Boolean canTokenBeRefreshed(String token) throws Exception {
		//log.debug("Start check canTokenBeRefreshed");
		final Date created = getCreatedDateFromToken(token);
		//log.debug("Token Created At : {}", created);
		return isCreatedBeforeLastPasswordReset(created, new Date()) && !isTokenExpired(token);
	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.setIssuedAt(new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			log.error("Token cannot be refreshed");
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public Boolean validateToken(String token) throws Exception {
		return !isTokenExpired(token);
	}
}

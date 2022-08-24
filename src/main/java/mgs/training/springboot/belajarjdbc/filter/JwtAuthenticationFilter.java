package mgs.training.springboot.belajarjdbc.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import mgs.training.springboot.belajarjdbc.constant.HeaderConstant;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.dto.login.LoginDto;
import mgs.training.springboot.belajarjdbc.dto.login.UserAuthorityDto;
import mgs.training.springboot.belajarjdbc.service.AppCacheService;
import mgs.training.springboot.belajarjdbc.service.LoginService;
import mgs.training.springboot.belajarjdbc.util.JwtTokenUtil;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final LoginService service;
	private final AppCacheService cacheService;
	private final JwtTokenUtil jwtTokenUtil;
	private final ObjectMapper objectMapper;
	
	public JwtAuthenticationFilter(LoginService service, AppCacheService cacheService, JwtTokenUtil jwtTokenUtil, ObjectMapper objectMapper) {
		this.service = service;
		this.cacheService = cacheService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(!request.getRequestURI().startsWith("/api/auth/login")) {
			String uri = request.getRequestURI();
			String method = request.getMethod();
			log.info("validate uri {}", uri);
			
			try {
				String authToken = request.getHeader(HeaderConstant.AUTHORIZATION);
				if (StringUtils.isBlank(authToken)) {
					throw new BadCredentialsException("Token is invalid");
				}
				
				if (authToken.startsWith("Bearer")) {
					authToken = authToken.substring(7);
				}
				
				String username = null;
				
				if (jwtTokenUtil.validateToken(authToken)) {
					/* as usual */
					Claims claim = jwtTokenUtil.getClaimsFromToken(authToken);
					LoginDto loginDto = objectMapper.readValue(claim.getSubject(), LoginDto.class);
					username = loginDto.getUsername();
					log.debug("User {} accesses URL {}", username, request.getRequestURI());
					
					if (!service.validateToken(loginDto.getSessionId(), authToken)) {
						throw new BadCredentialsException("Token is invalid");
					}
					
					UsernamePasswordAuthenticationToken authentication
					= new UsernamePasswordAuthenticationToken(loginDto, null, Collections.EMPTY_LIST);
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
					Boolean isAuthorized = isRequestAuthorized(uri, method,  username);
					if(!isAuthorized) throw new Exception("Permission Denied!");
				} else {
					throw new BadCredentialsException("Token Expired");
				}
			} catch (Exception e) {
				log.error("Authentication failed, cause:{}", e.getMessage());
				String exMsg = e.getMessage();
				
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
				
				if (exMsg.toLowerCase().contains("expired")) {
					exMsg = "Token expired";
				} else {
					exMsg = e.getMessage();
				}
				
				response.setContentType(MediaType.APPLICATION_JSON_VALUE);
				HttpRespModel<?> respModel = HttpRespModel.error(response.getStatus(), exMsg);
				try (PrintWriter writer = response.getWriter()) {
					writer.write(objectMapper.writeValueAsString(respModel));
					writer.flush();
				} catch (Exception ex) {
					log.error("Failed to send response, cause:{}", ex.getMessage());
				}
				
				SecurityContextHolder.clearContext();
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
	
	private boolean isRequestAuthorized(String uri, String method, String username) {
    	UserAuthorityDto authorityDto = cacheService.getGroupPrivilege(username);
    	if(authorityDto==null) {
    		log.warn("List authority for username {} is not found!", username);
    		return false;
    	}
    	
    	Boolean resultBool = false;
    	resultBool = authorityDto.getUrlByMethodStartWith().parallelStream()
    			.anyMatch(auth -> uri.startsWith(auth.split("\\|")[0].replaceAll("\\*", "")) 
        				&& method.equalsIgnoreCase(auth.split("\\|")[1]));
    	if(resultBool) {
    		log.info("user:{} accessed:{}-{} Passed at first checking", username, uri, method);
    		return resultBool;
    	}
    	
    	resultBool = authorityDto.getUrlByMethodExact().parallelStream()
    			.anyMatch(auth -> uri.equals(auth.split("\\|")[0]) && method.equalsIgnoreCase(auth.split("\\|")[1]));
    	if(resultBool) {
    		log.info("user:{} accessed:{}-{} Passed at second checking", username, uri, method);
    		return resultBool;
    	}
    	
    	resultBool = authorityDto.getUrlStartWith().parallelStream()
    			.anyMatch(auth -> uri.startsWith(auth.replaceAll("\\*", "")));
    	if(resultBool) {
    		log.info("user:{} accessed:{}-{} Passed at third checking", username, uri, method);
    		return resultBool;
    	}
    	
    	resultBool = authorityDto.getUrlExact().parallelStream().anyMatch(auth -> uri.equals(auth));
    	log.info("user:{} accessed:{}-{} result from last checking is {}", username, uri, method, resultBool);
    	return resultBool;
    }

}

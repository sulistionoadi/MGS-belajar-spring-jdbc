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
import mgs.training.springboot.belajarjdbc.dto.LoginDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.service.LoginService;
import mgs.training.springboot.belajarjdbc.util.JwtTokenUtil;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final LoginService service;
	private final JwtTokenUtil jwtTokenUtil;
	private final ObjectMapper objectMapper;
	
	public JwtAuthenticationFilter(LoginService service, JwtTokenUtil jwtTokenUtil, ObjectMapper objectMapper) {
		this.service = service;
		this.jwtTokenUtil = jwtTokenUtil;
		this.objectMapper = objectMapper;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		if(request.getRequestURI().startsWith("/api/auth/login")) {
			filterChain.doFilter(request, response);
		}

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
        
		filterChain.doFilter(request, response);
	}

}

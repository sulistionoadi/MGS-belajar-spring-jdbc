package mgs.training.springboot.belajarjdbc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import mgs.training.springboot.belajarjdbc.filter.JwtAuthenticationFilter;
import mgs.training.springboot.belajarjdbc.service.AppCacheService;
import mgs.training.springboot.belajarjdbc.service.LoginService;
import mgs.training.springboot.belajarjdbc.util.JwtTokenUtil;

@Configuration
public class FilterConfig {

    private final ObjectMapper objectMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginService loginService;
    private final AppCacheService cacheService;

    @Autowired
    public FilterConfig(ObjectMapper objectMapper, JwtTokenUtil jwtTokenUtil, LoginService loginService, AppCacheService cacheService) {
    	this.objectMapper = objectMapper;
    	this.jwtTokenUtil = jwtTokenUtil;
    	this.loginService = loginService;
    	this.cacheService = cacheService;
    }

    @Bean
    public FilterRegistrationBean<?> jwtTokenFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtAuthenticationFilter(loginService, cacheService, jwtTokenUtil, objectMapper));
        registrationBean.setUrlPatterns(Arrays.asList("/api/*"));
        registrationBean.setOrder(1);
        return registrationBean;
    }


}

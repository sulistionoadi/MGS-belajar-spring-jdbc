package mgs.training.springboot.belajarjdbc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import mgs.training.springboot.belajarjdbc.filter.JwtAuthenticationFilter;
import mgs.training.springboot.belajarjdbc.service.LoginService;
import mgs.training.springboot.belajarjdbc.util.JwtTokenUtil;

@Configuration
public class FilterConfig {

    private final ObjectMapper objectMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginService loginService;

    @Autowired
    public FilterConfig(ObjectMapper objectMapper, JwtTokenUtil jwtTokenUtil, LoginService loginService) {
    	this.objectMapper = objectMapper;
    	this.jwtTokenUtil = jwtTokenUtil;
    	this.loginService = loginService;
    }

    @Bean
    public FilterRegistrationBean<?> jwtTokenFilter() {
        FilterRegistrationBean<JwtAuthenticationFilter> registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtAuthenticationFilter(loginService, jwtTokenUtil, objectMapper));
        registrationBean.setUrlPatterns(Arrays.asList("/api/*"));
        registrationBean.setOrder(1);
        return registrationBean;
    }


}

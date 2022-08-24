package mgs.training.springboot.belajarjdbc.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import mgs.training.springboot.belajarjdbc.constant.ErrorCode;
import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.login.LoginDto;
import mgs.training.springboot.belajarjdbc.dto.login.UserAuthorityDto;

public class AuthUtil {

	public static LoginDto getProfile() {
		LoginDto dto = (LoginDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(dto==null) throw new CustomException(ErrorCode.UNAUTHENTICATED, "Invalid Session");
		return dto;
	}
	
	public static UserAuthorityDto buildObjectUserAuthority(List<String> listAuthorities) {
		List<String> urlByMethodExact = new ArrayList<>();
		List<String> urlByMethodStartWith = new ArrayList<>();
		List<String> urlExact = new ArrayList<>();
		List<String> urlStartWith = new ArrayList<>();
		
		for (String authority : listAuthorities) {
			if(authority.contains("**") && authority.contains("|")) {
				urlByMethodStartWith.add(authority);
			} else if(authority.contains("|")) {
				urlByMethodExact.add(authority);
			} else if(authority.contains("**")) {
				urlStartWith.add(authority);
			} else {
				urlExact.add(authority);
			}
		}
		
		UserAuthorityDto dto = new UserAuthorityDto();
		dto.setUrlByMethodStartWith(urlByMethodStartWith);
		dto.setUrlByMethodExact(urlByMethodExact);
		dto.setUrlStartWith(urlStartWith);
		dto.setUrlExact(urlExact);
		return dto;
	}
	
}

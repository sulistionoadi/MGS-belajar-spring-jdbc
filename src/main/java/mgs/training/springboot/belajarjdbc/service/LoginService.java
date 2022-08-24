package mgs.training.springboot.belajarjdbc.service;

import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.dto.login.LoginDto;

public interface LoginService {

	public HttpRespModel login(LoginDto dto);
	public HttpRespModel logout();
	
	public Boolean validateToken(Long sessionId, String token);

}

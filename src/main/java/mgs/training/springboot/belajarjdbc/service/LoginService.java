package mgs.training.springboot.belajarjdbc.service;

import mgs.training.springboot.belajarjdbc.dto.LoginDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;

public interface LoginService {

	public HttpRespModel login(LoginDto dto);
	public HttpRespModel logout();
	
	public Boolean validateToken(Long sessionId, String token);

}

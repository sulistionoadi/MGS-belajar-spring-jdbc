package mgs.training.springboot.belajarjdbc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mgs.training.springboot.belajarjdbc.dto.LoginDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.service.LoginService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private final LoginService service;
	
	public AuthenticationController(@Qualifier("loginJpaService") LoginService service) {
		this.service = service;
	}

	@PostMapping("/login")
	public HttpRespModel<LoginDto> login(@RequestBody @Valid LoginDto login){
		return service.login(login);
	}
	
}

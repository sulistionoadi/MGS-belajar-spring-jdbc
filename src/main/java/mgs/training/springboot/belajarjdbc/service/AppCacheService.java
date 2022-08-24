package mgs.training.springboot.belajarjdbc.service;

import mgs.training.springboot.belajarjdbc.dto.login.UserAuthorityDto;

public interface AppCacheService {

	public UserAuthorityDto getGroupPrivilege(String username);
	public UserAuthorityDto putGroupPrivilege(String username, UserAuthorityDto authorityObj);
	
}

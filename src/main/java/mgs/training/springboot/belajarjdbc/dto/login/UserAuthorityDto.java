package mgs.training.springboot.belajarjdbc.dto.login;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class UserAuthorityDto implements Serializable {

	private static final long serialVersionUID = 3441967937948907316L;
	private Long userId;
	private String username;
	private List<String> urlByMethodExact;
	private List<String> urlByMethodStartWith;
	private List<String> urlExact;
	private List<String> urlStartWith;
	
}
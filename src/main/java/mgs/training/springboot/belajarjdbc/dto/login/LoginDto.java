package mgs.training.springboot.belajarjdbc.dto.login;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDto {

	private Long sessionId;
	@NotBlank(message="Username is Required")
	private String username;
	@NotBlank(message="Password is Required")
	private String password;
	private String token;
	
	private Date createdAt;
	private Date expiredAt;
	
}

package mgs.training.springboot.belajarjdbc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class PenggunaDto {

	private Long id;
	private String username;
	private String password;
	private boolean active;
	
	private JabatanDto jabatan;
	private MasterUnitDto unit;

	@Builder
	public PenggunaDto(Long id, String username, String password, boolean active, JabatanDto jabatan,
			MasterUnitDto unit) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
		this.jabatan = jabatan;
		this.unit = unit;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
}

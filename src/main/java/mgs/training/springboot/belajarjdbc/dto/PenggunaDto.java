package mgs.training.springboot.belajarjdbc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
public class PenggunaDto {

	@Getter @Setter
	private Long id;
	@Getter @Setter
	private String username;
	@Setter
	private String password;
	@Getter @Setter
	private boolean active;
	
	@Getter @Setter
	private JabatanDto jabatan;
	@Getter @Setter
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

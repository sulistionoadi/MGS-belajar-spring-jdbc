package mgs.training.springboot.belajarjdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter @Setter
//@ToString
//@NoArgsConstructor
public class PenggunaDto {

	private Long id;
	private String username;
	private String password;
	private boolean active;
	
	private JabatanDto jabatan;
	private MasterUnitDto unit;

	public PenggunaDto() { }
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public JabatanDto getJabatan() {
		return jabatan;
	}

	public void setJabatan(JabatanDto jabatan) {
		this.jabatan = jabatan;
	}

	public MasterUnitDto getUnit() {
		return unit;
	}

	public void setUnit(MasterUnitDto unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "PenggunaDto [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active  + "]";
//				+ ", jabatan=" + jabatan==null ? "N/A" : jabatan.toString();
//				+ ", unit=" + unit==null ? "N/A" : unit.toString() + "]";
	}
	
}

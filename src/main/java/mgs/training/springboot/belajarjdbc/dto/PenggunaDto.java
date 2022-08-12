package mgs.training.springboot.belajarjdbc.dto;

public class PenggunaDto {

	private Long id;
	private String username;
	private String password;
	private boolean active;
	
	private JabatanDto jabatan;
	private MasterUnitDto unit;
	
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
		return "PenggunaDto [id=" + id + ", username=" + username + ", password=" + password + ", active=" + active
				+ ", jabatan=" + jabatan + ", unit=" + unit + "]";
	}
	
}

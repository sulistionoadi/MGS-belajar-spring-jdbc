package mgs.training.springboot.belajarjdbc.dto;

public class JabatanDto {

	private Long id;
	private String namaJabatan;
	private boolean active;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNamaJabatan() {
		return namaJabatan;
	}
	public void setNamaJabatan(String namaJabatan) {
		this.namaJabatan = namaJabatan;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return "JabatanDto [id=" + id + ", namaJabatan=" + namaJabatan + ", active=" + active + "]";
	}
	
}

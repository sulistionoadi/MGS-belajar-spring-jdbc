package mgs.training.springboot.belajarjdbc.dto;

public class MasterUnitDto {

	private Long id;
	private String kode;
	private String nama;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKode() {
		return kode;
	}
	public void setKode(String kode) {
		this.kode = kode;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	@Override
	public String toString() {
		return "MasterUnit [id=" + id + ", kode=" + kode + ", nama=" + nama + "]";
	}
	
}

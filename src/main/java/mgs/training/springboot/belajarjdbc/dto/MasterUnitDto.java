package mgs.training.springboot.belajarjdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Getter @Setter
//@ToString
//@NoArgsConstructor
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
	
	public MasterUnitDto() {
	}

	@Builder
	public MasterUnitDto(Long id, String kode, String nama) {
		this.id = id;
		this.kode = kode;
		this.nama = nama;
	}

	@Override
	public String toString() {
		return "MasterUnitDto [id=" + id + ", kode=" + kode + ", nama=" + nama + "]";
	}
	
	
}

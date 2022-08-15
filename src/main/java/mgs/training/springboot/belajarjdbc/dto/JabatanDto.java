package mgs.training.springboot.belajarjdbc.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class JabatanDto implements Comparable<JabatanDto>{

	private Long id;
	
	@NotBlank(message="Nama Jabatan harus diisi")
	@Pattern(regexp = "^[a-zA-Z0-9 '.-]{1,50}$", message = "Invalid Jabatan Name")
	private String namaJabatan;
	private boolean active;
	
	//Constructor
	public JabatanDto(Long id, String namaJabatan, boolean active) {
		this.id = id;
		this.namaJabatan = namaJabatan;
		this.active = active;
	}
	
	public JabatanDto() {
	}

	//Method
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JabatanDto other = (JabatanDto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int compareTo(JabatanDto o) {
		return this.getId().compareTo(o.getId());
	}
	
}

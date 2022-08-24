package mgs.training.springboot.belajarjdbc.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class JabatanDto implements Comparable<JabatanDto>{

	private Long id;
	
	@NotBlank(message="Nama Jabatan harus diisi")
	@Pattern(regexp = "^[a-zA-Z0-9 ]{1,50}$", message = "Invalid Jabatan Name")
	private String namaJabatan;
	private Boolean active;
	
	@Builder
	public JabatanDto(Long id, String namaJabatan, Boolean active) {
		this.id = id;
		this.namaJabatan = namaJabatan;
		this.active = active;
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
		return id.compareTo(o.getId());
	}
	
}

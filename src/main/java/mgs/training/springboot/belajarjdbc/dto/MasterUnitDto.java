package mgs.training.springboot.belajarjdbc.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@NoArgsConstructor
public class MasterUnitDto {

	private Long id;
	private String kode;
	private String nama;
	
	@Builder
	public MasterUnitDto(Long id, String kode, String nama) {
		this.id = id;
		this.kode = kode;
		this.nama = nama;
	}

}

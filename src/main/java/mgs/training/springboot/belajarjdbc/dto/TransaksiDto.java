package mgs.training.springboot.belajarjdbc.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiDto {

	private Long id;
	private Date createdAt;
	private String createdBy;
	private String nomorNota;
	private List<TransaksiDetailDto> detail;

}

package mgs.training.springboot.belajarjdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mgs.training.springboot.belajarjdbc.entity.TransaksiDetailPK;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiDetailDto {

    private TransaksiDetailPK id;
	private TransaksiDto transaksi;
	private String kodeBarang;
	private Integer jumlah;

}

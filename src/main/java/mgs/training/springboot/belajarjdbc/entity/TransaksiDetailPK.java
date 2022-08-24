package mgs.training.springboot.belajarjdbc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiDetailPK implements Serializable {

	private static final long serialVersionUID = 4830243261189160241L;

	@Column(name="transaksi_id", nullable = false)
	private Long transaksiId;
	
	@Column(name="kode_barang", length = 20, nullable = false)
	private String kodeBarang;

}

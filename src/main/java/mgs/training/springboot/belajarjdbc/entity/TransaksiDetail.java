package mgs.training.springboot.belajarjdbc.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TRANSAKSI_DETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransaksiDetail {

	@EmbeddedId
    private TransaksiDetailPK id;
	
	@MapsId("transaksiId")
    @ManyToOne
    @JoinColumn(name = "transaksi_id", insertable = false, updatable = false)
	private Transaksi transaksi;
	
	@Column(name = "kode_barang", length=20, insertable = false, updatable = false)
	private String kodeBarang;
	
	private Integer jumlah;

}

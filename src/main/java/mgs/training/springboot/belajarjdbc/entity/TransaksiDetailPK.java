package mgs.training.springboot.belajarjdbc.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TransaksiDetailPK implements Serializable {

	private static final long serialVersionUID = 4830243261189160241L;

	@Column(name="transaksi_id", nullable = false)
	private Long transaksiId;
	
	@Column(name="kode_barang", length = 20, nullable = false)
	private String kodeBarang;

	public TransaksiDetailPK() {}
	public TransaksiDetailPK(Long transaksiId, String kodeBarang) {
		this.transaksiId = transaksiId;
		this.kodeBarang = kodeBarang;
	}

	@Override
	public int hashCode() {
		return Objects.hash(kodeBarang, transaksiId);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransaksiDetailPK other = (TransaksiDetailPK) obj;
		return Objects.equals(kodeBarang, other.kodeBarang) && Objects.equals(transaksiId, other.transaksiId);
	}
	
}

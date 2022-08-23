package mgs.training.springboot.belajarjdbc.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="TRANSAKSI_DETAIL")
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

	public TransaksiDetailPK getId() {
		return id;
	}

	public void setId(TransaksiDetailPK id) {
		this.id = id;
	}

	public Transaksi getTransaksi() {
		return transaksi;
	}

	public void setTransaksi(Transaksi transaksi) {
		this.transaksi = transaksi;
	}

	public String getKodeBarang() {
		return kodeBarang;
	}

	public void setKodeBarang(String kodeBarang) {
		this.kodeBarang = kodeBarang;
	}

	public Integer getJumlah() {
		return jumlah;
	}

	public void setJumlah(Integer jumlah) {
		this.jumlah = jumlah;
	}
	
	
}

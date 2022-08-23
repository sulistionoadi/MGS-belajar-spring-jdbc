package mgs.training.springboot.belajarjdbc.dto;

import mgs.training.springboot.belajarjdbc.entity.TransaksiDetailPK;

public class TransaksiDetailDto {

    private TransaksiDetailPK id;
	private TransaksiDto transaksi;
	private String kodeBarang;
	private Integer jumlah;

	public TransaksiDetailPK getId() {
		return id;
	}

	public void setId(TransaksiDetailPK id) {
		this.id = id;
	}

	public TransaksiDto getTransaksi() {
		return transaksi;
	}

	public void setTransaksi(TransaksiDto transaksi) {
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

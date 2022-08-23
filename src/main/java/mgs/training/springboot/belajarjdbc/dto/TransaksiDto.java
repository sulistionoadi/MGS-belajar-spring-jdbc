package mgs.training.springboot.belajarjdbc.dto;

import java.util.Date;
import java.util.List;

public class TransaksiDto {

	private Long id;
	private Date createdAt;
	private String createdBy;
	private String nomorNota;
	private List<TransaksiDetailDto> detail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getNomorNota() {
		return nomorNota;
	}

	public void setNomorNota(String nomorNota) {
		this.nomorNota = nomorNota;
	}

	public List<TransaksiDetailDto> getDetail() {
		return detail;
	}

	public void setDetail(List<TransaksiDetailDto> detail) {
		this.detail = detail;
	}
	
	
}

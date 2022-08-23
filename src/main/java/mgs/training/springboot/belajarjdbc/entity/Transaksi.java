package mgs.training.springboot.belajarjdbc.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="TRANSAKSI")
public class Transaksi {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaksi_sequence")
    @SequenceGenerator(sequenceName = "SEQ_TRANSAKSI", allocationSize = 1, name = "transaksi_sequence")
	@Column(name="ID")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	private String createdBy;
	
	private String nomorNota;
	
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy = "transaksi", cascade = CascadeType.ALL)
	private List<TransaksiDetail> detail;

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

	public List<TransaksiDetail> getDetail() {
		return detail;
	}

	public void setDetail(List<TransaksiDetail> detail) {
		this.detail = detail;
	}
	
}

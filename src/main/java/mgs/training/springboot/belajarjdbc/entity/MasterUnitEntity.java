package mgs.training.springboot.belajarjdbc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MASTER_UNIT")
//@Getter @Setter
@ToString
//@NoArgsConstructor
public class MasterUnitEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_sequence")
    @SequenceGenerator(sequenceName = "SEQ_MST_UNIT", allocationSize = 1, name = "unit_sequence")
	@Column(name="ID")
	private Long id;
	
	@Column(name="KODE_UNIT", length = 20)
	private String kode;
	
	@Column(name="NAMA_UNIT", length = 50)
	private String nama;
	
	@Column(name="IS_ACTIVE", columnDefinition = "NUMBER(1)")
	private Boolean active;

	public MasterUnitEntity() {
	}
	
	@Builder
	public MasterUnitEntity(Long id, String kode, String nama, Boolean active) {
		this.id = id;
		this.kode = kode;
		this.nama = nama;
		this.active = active;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}

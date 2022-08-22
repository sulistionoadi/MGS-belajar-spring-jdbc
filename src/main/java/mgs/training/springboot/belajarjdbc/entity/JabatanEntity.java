package mgs.training.springboot.belajarjdbc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="JABATAN")
//@Getter @Setter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class JabatanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jabatan_sequence")
    @SequenceGenerator(sequenceName = "SEQ_JABATAN", allocationSize = 1, name = "jabatan_sequence")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAMA_JABATAN")
	private String namaJabatan;
	
	@Column(name="IS_ACTIVE", columnDefinition = "NUMBER(1)")
	private Boolean active;

	public JabatanEntity() {}
	
	@Builder
	public JabatanEntity(Long id, String namaJabatan, Boolean active) {
		this.id = id;
		this.namaJabatan = namaJabatan;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNamaJabatan() {
		return namaJabatan;
	}

	public void setNamaJabatan(String namaJabatan) {
		this.namaJabatan = namaJabatan;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
}

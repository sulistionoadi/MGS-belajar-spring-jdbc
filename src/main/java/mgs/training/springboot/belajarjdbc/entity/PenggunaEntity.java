package mgs.training.springboot.belajarjdbc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="PENGGUNA")
//@Getter @Setter
@ToString
//@NoArgsConstructor
public class PenggunaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pengguna_sequence")
    @SequenceGenerator(sequenceName = "SEQ_PENGGUNA", allocationSize = 1, name = "pengguna_sequence")
	@Column(name="ID")
	private Long id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="IS_ACTIVE", columnDefinition = "NUMBER(1)")
	private Boolean active;
	
	@ManyToOne
	@JoinColumn(name = "ID_JABATAN")
	private JabatanEntity jabatan;
	
	@ManyToOne
	@JoinColumn(name = "ID_UNIT")
	private MasterUnitEntity unit;

	public PenggunaEntity() {}
	
	@Builder
	public PenggunaEntity(Long id, String username, String password, Boolean active, JabatanEntity jabatan,
			MasterUnitEntity unit) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
		this.jabatan = jabatan;
		this.unit = unit;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public JabatanEntity getJabatan() {
		return jabatan;
	}

	public void setJabatan(JabatanEntity jabatan) {
		this.jabatan = jabatan;
	}

	public MasterUnitEntity getUnit() {
		return unit;
	}

	public void setUnit(MasterUnitEntity unit) {
		this.unit = unit;
	}

}

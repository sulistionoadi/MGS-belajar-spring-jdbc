package mgs.training.springboot.belajarjdbc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="JABATAN")
@Getter @Setter
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
	
}

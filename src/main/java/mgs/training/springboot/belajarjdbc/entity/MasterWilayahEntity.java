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
@Table(name = "MASTER_WILAYAH")
@Getter @Setter
public class MasterWilayahEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wilayah_sequence")
    @SequenceGenerator(sequenceName = "SEQ_MST_WILAYAH", allocationSize = 1, name = "wilayah_sequence")
	@Column(name="ID")
	private Long id;
	
	@Column(name="KODE_WIL", length = 20)
	private String kode;
	
	@Column(name="NAMA_WIL", length = 50)
	private String nama;
	
	@Column(name="IS_ACTIVE", columnDefinition = "NUMBER(1)")
	private Boolean active;
	
}

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="PENGGUNA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}

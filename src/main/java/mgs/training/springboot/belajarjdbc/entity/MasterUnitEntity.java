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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import mgs.training.springboot.belajarjdbc.dto.TransaksiDetailDto;
import mgs.training.springboot.belajarjdbc.dto.TransaksiDto;

@Entity
@Table(name = "MASTER_UNIT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}

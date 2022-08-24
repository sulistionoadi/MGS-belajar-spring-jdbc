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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mgs.training.springboot.belajarjdbc.dto.TransaksiDetailDto;
import mgs.training.springboot.belajarjdbc.dto.TransaksiDto;

@Entity
@Table(name="TRANSAKSI")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}

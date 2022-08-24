package mgs.training.springboot.belajarjdbc.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="JABATAN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ROLE_MENU",
            joinColumns = @JoinColumn(name = "id_jabatan", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "id_menu", nullable = false)
    )
    private Set<MenuEntity> menuSet = new HashSet<MenuEntity>();
	
}

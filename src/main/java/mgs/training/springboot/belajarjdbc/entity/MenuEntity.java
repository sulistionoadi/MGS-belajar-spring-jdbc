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
import lombok.NoArgsConstructor;

@Entity
@Table(name="MENU")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_sequence")
    @SequenceGenerator(sequenceName = "SEQ_MENU", allocationSize = 1, name = "menu_sequence")
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAMA_MENU")
	private String namaMenu;
	
	@Column(name="MENU_LINK")
	private String menuLink;
	
	@Column(name="IS_ACTIVE", columnDefinition = "NUMBER(1)")
	private Boolean active;
	
}

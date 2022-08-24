package mgs.training.springboot.belajarjdbc.entity;

import java.util.Date;

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
@Table(name="SESSION_LOGIN")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session_sequence")
    @SequenceGenerator(sequenceName = "SEQ_SESSION", allocationSize = 1, name = "session_sequence")
	@Column(name="ID")
	private Long id;
	private Date createdAt;
	private Long userId;
	@Column(columnDefinition = "VARCHAR2(4000)")
	private String token;
	
}

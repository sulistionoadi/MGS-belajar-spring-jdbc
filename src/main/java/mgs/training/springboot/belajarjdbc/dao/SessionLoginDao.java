package mgs.training.springboot.belajarjdbc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import mgs.training.springboot.belajarjdbc.entity.SessionLogin;

@Repository
public interface SessionLoginDao extends JpaRepository<SessionLogin, Long>{

	@Modifying
	void deleteByUserId(Long userId);
}

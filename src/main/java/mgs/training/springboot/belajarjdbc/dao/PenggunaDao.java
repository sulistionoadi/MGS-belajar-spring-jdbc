package mgs.training.springboot.belajarjdbc.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mgs.training.springboot.belajarjdbc.entity.PenggunaEntity;

public interface PenggunaDao extends JpaRepository<PenggunaEntity, Long>{

	@Query("SELECT j FROM PenggunaEntity j WHERE (:filter IS NULL OR lower(j.username) LIKE lower(:filter) )")
	Page<PenggunaEntity> findByUsername(@Param("filter") String filter, Pageable page);
	
}

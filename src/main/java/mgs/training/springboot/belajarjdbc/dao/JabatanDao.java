package mgs.training.springboot.belajarjdbc.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mgs.training.springboot.belajarjdbc.entity.JabatanEntity;

public interface JabatanDao extends JpaRepository<JabatanEntity, Long>{

	//findBy + namaField
	//List<JabatanEntity> findByNamaJabatanLikeIgnoreCase(String namaJabatan);
	
	@Query("SELECT j FROM JabatanEntity j WHERE (:namaJabatan IS NULL OR lower(j.namaJabatan) LIKE lower(:namaJabatan) )")
	Page<JabatanEntity> findByNama(String namaJabatan, Pageable page);
	
}

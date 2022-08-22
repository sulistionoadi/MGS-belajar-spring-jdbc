package mgs.training.springboot.belajarjdbc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import mgs.training.springboot.belajarjdbc.entity.MasterUnitEntity;

public interface MasterUnitDao extends JpaRepository<MasterUnitEntity, Long>{

}

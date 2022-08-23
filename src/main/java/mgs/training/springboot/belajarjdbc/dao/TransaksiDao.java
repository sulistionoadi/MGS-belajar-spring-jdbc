package mgs.training.springboot.belajarjdbc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mgs.training.springboot.belajarjdbc.entity.Transaksi;

@Repository
public interface TransaksiDao extends JpaRepository<Transaksi, Long>{

}

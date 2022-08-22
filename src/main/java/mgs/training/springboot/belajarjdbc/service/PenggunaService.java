package mgs.training.springboot.belajarjdbc.service;

import java.util.List;

import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;

public interface PenggunaService {

	public void save(PenggunaDto dto);
	public void update(PenggunaDto dto);
	public void delete(Long id);
	public List<PenggunaDto> getData(String filter);
	
}

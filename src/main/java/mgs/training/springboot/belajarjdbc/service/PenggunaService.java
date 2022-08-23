package mgs.training.springboot.belajarjdbc.service;

import org.springframework.data.domain.Pageable;

import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;

public interface PenggunaService {

	public HttpRespModel save(PenggunaDto dto);
	public HttpRespModel update(PenggunaDto dto);
	public HttpRespModel delete(Long id);
	public HttpPagedModel<PenggunaDto> getData(String filter, Pageable page);
	
}

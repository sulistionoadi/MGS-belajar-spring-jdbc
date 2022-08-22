package mgs.training.springboot.belajarjdbc.service;

import org.springframework.data.domain.Pageable;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;

public interface JabatanService {

	public HttpRespModel save(JabatanDto dto);
	public HttpRespModel update(JabatanDto dto);
	public HttpRespModel delete(Long id);
	public HttpPagedModel<JabatanDto> getData(String filter, Pageable pageable);

}

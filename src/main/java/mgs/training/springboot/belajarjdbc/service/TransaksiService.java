package mgs.training.springboot.belajarjdbc.service;

import org.springframework.data.domain.Pageable;

import mgs.training.springboot.belajarjdbc.dto.TransaksiDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;

public interface TransaksiService {

	public HttpRespModel save(TransaksiDto dto);
	public HttpPagedModel<TransaksiDto> getData(Pageable pageable);

}

package mgs.training.springboot.belajarjdbc.service;

import java.util.List;

import org.springframework.lang.NonNull;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

public interface JabatanService {

	public void save(JabatanDto dto);
	public List<JabatanDto> getData(String filter);

}

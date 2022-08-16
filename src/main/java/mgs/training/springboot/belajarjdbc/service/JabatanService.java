package mgs.training.springboot.belajarjdbc.service;

import java.util.List;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

public interface JabatanService {

	public void save(JabatanDto dto);
	public void update(JabatanDto dto);
	public void delete(Long id);
	public List<JabatanDto> getData(String filter, int page, int size);

}

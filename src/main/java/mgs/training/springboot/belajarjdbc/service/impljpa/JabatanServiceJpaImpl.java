package mgs.training.springboot.belajarjdbc.service.impljpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mgs.training.springboot.belajarjdbc.dao.JabatanDao;
import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.entity.JabatanEntity;
import mgs.training.springboot.belajarjdbc.service.JabatanService;

@Service("jabatanJpaService")
public class JabatanServiceJpaImpl implements JabatanService {

	@Autowired private JabatanDao dao;
	
	@Override
	public void save(JabatanDto dto) {
		JabatanEntity entity = new JabatanEntity();
		entity.setNamaJabatan(dto.getNamaJabatan());
		entity.setActive(Boolean.TRUE);
		
		dao.save(entity);
	}

	@Override
	public List<JabatanDto> getData(String filter, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		if(StringUtils.hasText(filter)) {
			filter = "%" + filter + "%";
		}
		
		Page<JabatanEntity> listData = dao.findByNama(filter, pageRequest);
		List<JabatanDto> listDto = new ArrayList<JabatanDto>();
		
		System.out.println("Data :");
		listData.stream().forEach(x -> {
			JabatanDto dto = new JabatanDto(x.getId(), x.getNamaJabatan(), x.getActive());
			System.out.println(" -> " + dto.toString());
			listDto.add(dto);
		});
		return listDto;
	}

}

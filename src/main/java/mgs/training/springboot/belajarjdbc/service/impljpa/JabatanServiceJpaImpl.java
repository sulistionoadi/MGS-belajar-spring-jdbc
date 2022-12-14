package mgs.training.springboot.belajarjdbc.service.impljpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mgs.training.springboot.belajarjdbc.dao.JabatanDao;
import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.entity.JabatanEntity;
import mgs.training.springboot.belajarjdbc.service.JabatanService;

@Service("jabatanJpaService")
public class JabatanServiceJpaImpl implements JabatanService {

	@Autowired private JabatanDao dao;
	
	@Override
	public HttpRespModel save(JabatanDto dto) {
		JabatanEntity entity = new JabatanEntity();
		entity.setNamaJabatan(dto.getNamaJabatan());
		entity.setActive(Boolean.TRUE);
		
		dao.save(entity);
		return HttpRespModel.ok(null, "Save jabatan sukses");
	}

	@Override
	public HttpPagedModel<JabatanDto> getData(String filter, Pageable pageable) {
		if(StringUtils.hasText(filter)) {
			filter = "%" + filter + "%";
		}
		
		Page<JabatanEntity> listData = dao.findByNama(filter, pageable);
		List<JabatanDto> listDto = new ArrayList<JabatanDto>();
		
		System.out.println("Data :");
		listData.stream().forEach(x -> {
			JabatanDto dto = new JabatanDto(x.getId(), x.getNamaJabatan(), x.getActive());
			System.out.println(" -> " + dto.toString());
			listDto.add(dto);
		});
		return HttpPagedModel.ok(listDto, listData.getNumberOfElements(), listData.getTotalElements());
	}

	@Override
	public HttpRespModel update(JabatanDto dto) {
		Optional<JabatanEntity> exist = dao.findById(dto.getId());
		if(!exist.isPresent()) {
			throw new CustomException("Data tidak ditemukan");
		}
		
		JabatanEntity entity = exist.get();
		entity.setNamaJabatan(dto.getNamaJabatan());
		
		dao.save(entity);
		return HttpRespModel.ok(null, "Update jabatan sukses");
	}

	@Override
	public HttpRespModel delete(Long id) {
		Optional<JabatanEntity> exist = dao.findById(id);
		if(!exist.isPresent()) {
			throw new CustomException("Data tidak ditemukan");
		}
		
		dao.delete(exist.get());
		return HttpRespModel.ok(null, "Delete jabatan sukses");
	}

}

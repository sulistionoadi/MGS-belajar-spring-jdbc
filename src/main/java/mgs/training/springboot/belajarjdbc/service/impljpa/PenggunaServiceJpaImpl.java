package mgs.training.springboot.belajarjdbc.service.impljpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import mgs.training.springboot.belajarjdbc.constant.ErrorCode;
import mgs.training.springboot.belajarjdbc.dao.JabatanDao;
import mgs.training.springboot.belajarjdbc.dao.MasterUnitDao;
import mgs.training.springboot.belajarjdbc.dao.PenggunaDao;
import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.entity.JabatanEntity;
import mgs.training.springboot.belajarjdbc.entity.MasterUnitEntity;
import mgs.training.springboot.belajarjdbc.entity.PenggunaEntity;
import mgs.training.springboot.belajarjdbc.mapper.mapstruct.PenggunaMapper;
import mgs.training.springboot.belajarjdbc.service.PenggunaService;

@Service("penggunaJpaService")
public class PenggunaServiceJpaImpl implements PenggunaService {

	private PenggunaDao dao;
	private PenggunaMapper mapper;
	private JabatanDao jabatanDao;
	private MasterUnitDao unitDao;
	
	@Autowired
	public PenggunaServiceJpaImpl(PenggunaDao dao, PenggunaMapper mapper, JabatanDao jabatanDao,
			MasterUnitDao unitDao) {
		this.dao = dao;
		this.mapper = mapper;
		this.jabatanDao = jabatanDao;
		this.unitDao = unitDao;
	}

	@Override
	public HttpRespModel save(PenggunaDto dto) {
		PenggunaEntity toSave = mapper.toEntity(dto);
		
		Optional<JabatanEntity> opJabatan = jabatanDao.findById(dto.getJabatan().getId());
		if(opJabatan.isEmpty()) {
			throw new CustomException("Jabatan dengan id:"+dto.getJabatan().getId()+" tidak ditemukan");
		}
		
		Optional<MasterUnitEntity> opUnit = unitDao.findById(dto.getUnit().getId());
		if(opUnit.isEmpty()) {
			throw new CustomException("Unit dengan id:"+dto.getJabatan().getId()+" tidak ditemukan");
		}
		
		toSave.setJabatan(opJabatan.get());
		toSave.setUnit(opUnit.get());
		dao.save(toSave);
		return HttpRespModel.ok(null, "Pengguna berhasil di simpan");
	}

	@Override
	public HttpRespModel update(PenggunaDto dto) {
		PenggunaEntity toSave = mapper.toEntity(dto);
		Optional<PenggunaEntity> exist = dao.findById(dto.getId()); 
		if(exist.isEmpty()) {
			return HttpRespModel.error(ErrorCode.DATA_NOT_FOUND, "Pengguna dengan id:" + dto.getId() + "tidak ditemukan", null);
		}
		
		Optional<JabatanEntity> opJabatan = jabatanDao.findById(dto.getJabatan().getId());
		if(opJabatan.isEmpty()) {
			throw new CustomException("Jabatan dengan id:"+dto.getJabatan().getId()+" tidak ditemukan");
		}
		
		Optional<MasterUnitEntity> opUnit = unitDao.findById(dto.getUnit().getId());
		if(opUnit.isEmpty()) {
			throw new CustomException("Unit dengan id:"+dto.getJabatan().getId()+" tidak ditemukan");
		}
		
		toSave.setJabatan(opJabatan.get());
		toSave.setUnit(opUnit.get());
		toSave.setId(exist.get().getId());
		dao.save(toSave);
		return HttpRespModel.ok(null, "Pengguna berhasil di simpan");
	}

	@Override
	public HttpRespModel delete(Long id) {
		Optional<PenggunaEntity> exist = dao.findById(id); 
		if(exist.isEmpty()) {
			return HttpRespModel.error(ErrorCode.DATA_NOT_FOUND, "Pengguna dengan id:" + id + "tidak ditemukan", null);
		}
		return HttpRespModel.ok(null, "Pengguna berhasil di hapus");
	}

	@Override
	public HttpPagedModel<PenggunaDto> getData(String filter, Pageable page) {
		filter = StringUtils.hasText(filter) ? "%" + filter + "%" : null;
		Page<PenggunaEntity> data = dao.findByUsername(filter, page);
		
		return HttpPagedModel.ok(mapper.toDto(data.getContent()), "Success", data.getNumberOfElements(), data.getTotalElements());
	}
	
}

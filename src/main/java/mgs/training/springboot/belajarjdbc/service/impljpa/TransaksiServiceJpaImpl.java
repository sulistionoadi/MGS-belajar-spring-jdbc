package mgs.training.springboot.belajarjdbc.service.impljpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgs.training.springboot.belajarjdbc.dao.TransaksiDao;
import mgs.training.springboot.belajarjdbc.dto.TransaksiDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.entity.Transaksi;
import mgs.training.springboot.belajarjdbc.entity.TransaksiDetail;
import mgs.training.springboot.belajarjdbc.entity.TransaksiDetailPK;
import mgs.training.springboot.belajarjdbc.mapper.mapstruct.TransaksiMapper;
import mgs.training.springboot.belajarjdbc.service.TransaksiService;

@Service("transaksiJpaService")
public class TransaksiServiceJpaImpl implements TransaksiService {

	private TransaksiDao dao;
	private TransaksiMapper mapper;
	
	@Autowired
	public TransaksiServiceJpaImpl(TransaksiDao dao, TransaksiMapper mapper) {
		this.dao = dao;
		this.mapper = mapper;
	}

	@Override
	public HttpRespModel save(TransaksiDto dto) {
		Transaksi toSave = mapper.toEntity(dto);
		toSave.setCreatedAt(new Date());
		toSave.setCreatedBy("SYSTEM");
		
		List<TransaksiDetail> details = new ArrayList<TransaksiDetail>();
		dto.getDetail().forEach(d -> {
			//d.setTransaksi(toSave);
			TransaksiDetail det = new TransaksiDetail();
			det.setId(new TransaksiDetailPK(toSave.getId(), d.getKodeBarang()));
			det.setTransaksi(toSave);
			det.setKodeBarang(d.getKodeBarang());
			det.setJumlah(d.getJumlah());
			details.add(det);
		});
		toSave.setDetail(details);
		dao.save(toSave);
		return HttpRespModel.ok(null, "Transaksiberhasil di simpan");
	}

	@Override
	@Transactional(readOnly = true)
	public HttpPagedModel<TransaksiDto> getData(Pageable page) {
		Page<Transaksi> data = dao.findAll(page);
		data.forEach(h -> {
			h.getDetail().forEach(d -> {
				d.setTransaksi(null);
			});
		});
		List<TransaksiDto> listData = mapper.toDto(data.getContent());
		return HttpPagedModel.ok(listData, "Success", data.getNumberOfElements(), data.getTotalElements());
	}
	
}

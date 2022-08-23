package mgs.training.springboot.belajarjdbc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;
import mgs.training.springboot.belajarjdbc.dto.TransaksiDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.service.TransaksiService;

@RestController
@RequestMapping("/api/transaksi")
public class TransaksiController {

	private final TransaksiService service;
	
	public TransaksiController(@Qualifier("transaksiJpaService") TransaksiService service) {
		this.service = service;
	}

	@GetMapping
	public HttpPagedModel<TransaksiDto> listData(
            @PageableDefault(direction = Sort.Direction.DESC, sort="id") Pageable pageable){
		HttpPagedModel<TransaksiDto> list = service.getData(pageable);
		return list;
	}
	
	@PostMapping
	public HttpRespModel<PenggunaDto> save(@RequestBody @Valid TransaksiDto dto){
		return service.save(dto);
	}
		
}

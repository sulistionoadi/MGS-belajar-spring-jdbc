package mgs.training.springboot.belajarjdbc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.service.PenggunaService;

@RestController
@RequestMapping("/api/pengguna")
public class PenggunaController {

	private final PenggunaService penggunaService;
	
	public PenggunaController(@Qualifier("penggunaJpaService") PenggunaService penggunaService) {
		this.penggunaService = penggunaService;
	}

	@GetMapping
	public HttpPagedModel<PenggunaDto> listData(
			@RequestParam(name = "filter", required = false) String filter,
            @PageableDefault(direction = Sort.Direction.DESC, sort="id") Pageable pageable){
		HttpPagedModel<PenggunaDto> list = penggunaService.getData(filter, pageable);
		return list;
	}
	
	@PostMapping
	public HttpRespModel<PenggunaDto> save(@RequestBody @Valid PenggunaDto dto){
		if(dto.getId() != null) {
			return penggunaService.update(dto);
		} else {
			return penggunaService.save(dto);			
		}
	}
	
	@PostMapping("/delete/{id}")
	public HttpRespModel<PenggunaDto> delete(@PathVariable Long id){
		return penggunaService.delete(id);
	}
	
	@PutMapping
	public HttpRespModel<PenggunaDto> update(@RequestBody @Valid PenggunaDto dto){
		return penggunaService.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public HttpRespModel<PenggunaDto> deleteMethod(@PathVariable Long id){
		return penggunaService.delete(id);
	}
	
}

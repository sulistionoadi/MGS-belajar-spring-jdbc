package mgs.training.springboot.belajarjdbc.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.dto.http.HttpRespModel;
import mgs.training.springboot.belajarjdbc.service.JabatanService;

@RestController
@RequestMapping("/api/jabatan")
public class JabatanController {

	private final JabatanService jabatanService;
	
	public JabatanController(@Qualifier("jabatanPlsqlService") JabatanService jabatanService) {
		this.jabatanService = jabatanService;
	}

	@GetMapping
	public HttpPagedModel<JabatanDto> listData(
			@RequestParam(name = "filter", required = false) String filter,
            @PageableDefault(direction = Sort.Direction.DESC, sort="id") Pageable pageable){
		HttpPagedModel<JabatanDto> list = jabatanService.getData(filter, pageable);
		return list;
	}
	
	@PostMapping
	public HttpRespModel<JabatanDto> save(@RequestBody @Valid JabatanDto dto){
		if(dto.getId() != null) {
			return jabatanService.update(dto);
		} else {
			return jabatanService.save(dto);			
		}
	}
	
	@PostMapping("/delete/{id}")
	public HttpRespModel<JabatanDto> delete(@PathVariable Long id){
		return jabatanService.delete(id);
	}
	
	@PutMapping
	public HttpRespModel<JabatanDto> update(@RequestBody @Valid JabatanDto dto){
		return jabatanService.update(dto);
	}
	
	@DeleteMapping("/{id}")
	public HttpRespModel<JabatanDto> deleteMethod(@PathVariable Long id){
		return jabatanService.delete(id);
	}
	
	@PostMapping("/form")
	public HttpRespModel<JabatanDto> saveFormData(@Valid JabatanDto dto){
		if(dto.getId() != null) {
			return jabatanService.update(dto);
		} else {
			return jabatanService.save(dto);			
		}
	}
	
//	@RequestMapping(name = "/download", method=RequestMethod.GET, produces = "text/plain")
//	public String downloadDataJabatan(
//			@RequestParam(name = "filter", required = false) String filter,
//            @PageableDefault(direction = Sort.Direction.DESC, sort="id") Pageable pageable){
//		HttpPagedModel<JabatanDto> model = jabatanService.getData(filter, pageable);
//		if(!model.isSuccess()) {
//			throw new CustomException(model.getCode(), model.getMessage());
//		}
//		
//		StringBuilder builder = new StringBuilder();
//		List<JabatanDto> data = model.getData();
//		data.stream().forEach(d -> {
//			builder.append(d.getId()).append(";").append(d.getNamaJabatan()).append(";").append(d.getActive()).append("\n");
//		});
//		
//		return builder.toString();
//	}
	
}

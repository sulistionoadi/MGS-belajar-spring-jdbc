package mgs.training.springboot.belajarjdbc.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mgs.training.springboot.belajarjdbc.dto.CustomException;
import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.http.HttpPagedModel;
import mgs.training.springboot.belajarjdbc.service.JabatanService;

@Controller
@RequestMapping("/export")
public class ExportController {

	@Autowired
	@Qualifier("jabatanPlsqlService")
	private JabatanService jabatanService;
	
	
	@RequestMapping("/jabatan")
	public void downloadDataJabatan(
			@RequestParam(name = "filter", required = false) String filter,
            @PageableDefault(direction = Sort.Direction.DESC, sort="id") Pageable pageable, 
            HttpServletResponse response) throws IOException{
		HttpPagedModel<JabatanDto> model = jabatanService.getData(filter, pageable);
		if(!model.isSuccess()) {
			throw new CustomException(model.getCode(), model.getMessage());
		}
		
		StringBuilder builder = new StringBuilder();
		List<JabatanDto> data = model.getData();
		data.stream().forEach(d -> {
			builder.append(d.getId()).append(";").append(d.getNamaJabatan()).append(";").append(d.getActive()).append("\n");
		});
		
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data-jabatan.txt");
		response.setContentType(MediaType.TEXT_PLAIN_VALUE);
		response.getWriter().write(builder.toString());
		response.flushBuffer();
	}
	
}

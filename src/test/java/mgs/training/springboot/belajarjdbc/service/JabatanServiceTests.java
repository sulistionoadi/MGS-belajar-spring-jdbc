package mgs.training.springboot.belajarjdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

@SpringBootTest
public class JabatanServiceTests {

	@Autowired 
	//@Qualifier("jabatanJpaService")
	@Qualifier("jabatanPlsqlService")
	JabatanService service;
	
//	@Test
	public void getData() {
		service.getData(null, PageRequest.of(0, 10));
	}
	
	//@Test
	public void saveData() {
		JabatanDto dto = new JabatanDto();
//		dto.setNamaJabatan("DEBUGER");
		
		try {
			System.out.println("Sebelum panggil method save");
			service.save(dto);
			System.out.println("Setelah panggil method save");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			System.out.println("Selesai menjalankan method");
		}
	}
	
	@Test
	public void updateData() {
		JabatanDto dto = new JabatanDto();
		dto.setId(32L);
		dto.setNamaJabatan("OPERATOR");
		
		try {
			service.update(dto);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteData() {
		try {
			service.delete(43L);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
}

package mgs.training.springboot.belajarjdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

@SpringBootTest
public class JabatanServiceTests {

	@Autowired JabatanService service;
	
	@Test
	public void getData() {
		service.getData(null);
	}
	
	@Test
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
	
}

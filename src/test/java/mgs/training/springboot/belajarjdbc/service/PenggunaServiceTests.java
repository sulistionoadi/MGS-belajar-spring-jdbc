package mgs.training.springboot.belajarjdbc.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import mgs.training.springboot.belajarjdbc.dto.JabatanDto;
import mgs.training.springboot.belajarjdbc.dto.MasterUnitDto;
import mgs.training.springboot.belajarjdbc.dto.PenggunaDto;

@SpringBootTest
@ComponentScan(basePackages = {
	"mgs.training.springboot.belajarjdbc.dao",
	"mgs.training.springboot.belajarjdbc.mapper.mapstruct",
	"mgs.training.springboot.belajarjdbc.service"
})
@EntityScan(basePackages = "mgs.training.springboot.belajarjdbc.entity")
public class PenggunaServiceTests {

	@Autowired 
	@Qualifier("penggunaJpaService")
	private PenggunaService service;
	
	@Test
	public void getData() {
		List<PenggunaDto> list = service.getData(null);
		list.stream().forEach(dto -> {
			System.out.println("\n--- Print Data ---");
			System.out.println("-> " + dto.toString());
			
			if(dto.getJabatan()!=null) {
				System.out.println(" Jabatan -> " + dto.getJabatan().toString());				
			}
			
			if(dto.getUnit()!=null) {
				System.out.println(" Unit -> " + dto.getUnit().toString());				
			}
		});
	}
	
//	@Test
	public void saveData() {
//		PenggunaDto dto = PenggunaDto.builder()
//				.username("Adisu")
//				.password("p@ssw0rd")
//				.active(true)
//				.jabatan(JabatanDto.builder().id(1L).build())
//				.unit(MasterUnitDto.builder().id(1L).build())
//				.build();
		
		JabatanDto jabatan = new JabatanDto();
		jabatan.setId(1L);
		
		MasterUnitDto unit = new MasterUnitDto();
		unit.setId(1L);
		
		PenggunaDto dto = new PenggunaDto(null, "Adisu", "p@ssw0rd", true, jabatan, unit);
		
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
	
//	@Test
	public void updateData() {
//		JabatanDto dto = new JabatanDto();
//		dto.setId(32L);
//		dto.setNamaJabatan("OPERATOR");
//		
//		try {
//			service.update(dto);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
	}
	
//	@Test
	public void deleteData() {
//		try {
//			service.delete(43L);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			e.printStackTrace();
//		}
	}
	
}

package mgs.training.springboot.belajarjdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import mgs.training.springboot.belajarjdbc.comparator.JabatanSortDesc;
import mgs.training.springboot.belajarjdbc.dto.JabatanDto;

@SpringBootTest
class BelajarJdbcApplicationTests {

	//@Test
	void collectionsMap() {
		System.out.println("Start Belajar Collections Map");
		//Map<key, value>
		Map<String, String> mapData = new HashMap<String, String>();
		mapData.put("01", "Data 01");
		mapData.put("02", "Data 02");
		mapData.put("03", "Data 03");
		mapData.put("04", "Data 04");
		mapData.put("01", "Data 100");
		
//		for(String key : mapData.keySet()) {
//			System.out.println("Key: " + key + ", Value:" + mapData.get(key));
//		}
		
//		for(String val : mapData.values()) {
//			System.out.println(val);
//		}
		
		mapData.forEach((key, value) -> {
			System.out.println("Key: " + key + ", Value:" + mapData.get(key));
		});
		System.out.println("End Belajar Collections Map");
	}
	
	@Test
	void collectionsList() {
		System.out.println("Start Belajar Collections List");
		
		//List<JabatanDto>
		List<JabatanDto> data = new ArrayList<JabatanDto>();
		data.add(new JabatanDto(1L, "Manager", true));
		data.add(new JabatanDto(2L, "Supervisor", true));
		data.add(new JabatanDto(3L, "Staff", true));
		data.add(new JabatanDto(3L, "Staff", true));
		data.add(new JabatanDto(10L, "Jabatan 10", true));
		data.add(new JabatanDto(9L, "Jabatan 9", true));
		data.add(new JabatanDto(20L, "Jabatan 20", true));
		data.add(new JabatanDto(15L, "Jabatan 15", true));
		
//		for(JabatanDto dto : data) {
//			System.out.println(dto.toString());
//		}
		//Collections.sort(data);
		data.sort(new JabatanSortDesc());
		
		data.forEach(dto -> {
			System.out.println(dto.toString());
		});
		System.out.println("End Belajar Collections List");
	}
	
	//@Test
	void collectionsSet() {
		System.out.println("Start Belajar Collections HashSet");
		
		//List<JabatanDto>
		Set<JabatanDto> data = new HashSet<JabatanDto>();
		data.add(new JabatanDto(1L, "Manager", true));
		data.add(new JabatanDto(2L, "Supervisor", true));
		data.add(new JabatanDto(3L, "Staff", true));
		data.add(new JabatanDto(3L, "Staff", true));
		data.add(new JabatanDto(2L, "Supervisor", true));
		
//		for(JabatanDto dto : data) {
//			System.out.println(dto.toString());
//		}
		
		data.forEach(dto -> {
			System.out.println(dto.toString());
		});
		System.out.println("End Belajar Collections HashSet");
	}
	

}

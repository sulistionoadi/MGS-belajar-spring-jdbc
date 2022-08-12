package mgs.training.springboot.belajarjdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JabatanServiceTests {

	@Autowired JabatanService service;
	
	@Test
	public void getData() {
		service.getData(null);
	}
	
}

package mgs.training.springboot.belajarjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "mgs.training.springboot.belajarjdbc")
@EnableCaching
public class BelajarJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarJdbcApplication.class, args);
	}

}

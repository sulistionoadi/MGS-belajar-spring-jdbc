package mgs.training.springboot.belajarjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BelajarJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(BelajarJdbcApplication.class, args);
	}

}

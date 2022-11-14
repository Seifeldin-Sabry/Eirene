package be.kdg.eirene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class EireneSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EireneSpringApplication.class, args);
	}

}

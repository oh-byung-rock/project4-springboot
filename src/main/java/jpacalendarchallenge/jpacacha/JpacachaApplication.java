package jpacalendarchallenge.jpacacha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"jpacalendarchallenge.jpacacha.domain"})
public class JpacachaApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpacachaApplication.class, args);
	}
}

package proiect_spring.Proiect_IS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class ProiectIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectIsApplication.class, args);
	}
}

// http://localhost:8080/swagger-ui/index.html#/ // 
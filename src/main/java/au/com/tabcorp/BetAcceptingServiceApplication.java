package au.com.tabcorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("au.com.tabcorp.repositories")
public class BetAcceptingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetAcceptingServiceApplication.class, args);
	}

}

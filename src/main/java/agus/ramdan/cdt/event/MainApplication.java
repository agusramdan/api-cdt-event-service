package agus.ramdan.cdt.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
@EnableMongoRepositories(basePackages = "agus.ramdan.cdt.event.repository")
//@ComponentScan({"agus.ramdan.cdt.event","agus.ramdan.base.exception"})
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

}

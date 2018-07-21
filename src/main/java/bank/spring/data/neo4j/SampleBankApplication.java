package bank.spring.data.neo4j;


import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;



/**
 * @author Michael Hunger
 * @author Mark Angrish
 */
@SpringBootApplication
@EntityScan("bank.spring.data.neo4j.domain")
public class SampleBankApplication {
	
	public static void main(String[] args) throws IllegalStateException, IOException {
		SpringApplication.run(SampleBankApplication.class, args);
	}
}

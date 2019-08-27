package de.david.inez;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



//Testing
@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	
    public static void main(String[] args) {
    	
        SpringApplication.run(Application.class, args);
        
        log.info("Spring boot started.");
        
    }
}

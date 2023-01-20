package com.kilometer.kilo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(scanBasePackages={"controller","com.kilometer.kilo"})
@CrossOrigin(origins={"http://localhost:3001/","http://localhost:3000/","http://localhost:80/kilometrage/*"})
public class KiloApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(KiloApplication.class, args);
	}
	
}

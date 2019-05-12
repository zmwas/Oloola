package com.Oloola.Oloola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})

public class OloolaApplication {

	public static void main(String[] args) {
		SpringApplication.run(OloolaApplication.class, args);
	}

}

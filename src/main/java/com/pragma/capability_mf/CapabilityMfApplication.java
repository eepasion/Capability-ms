package com.pragma.capability_mf;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Capacidad Microservicio", version = "1.0", description = "Microservicio para gestionar las capacidades"))
public class CapabilityMfApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapabilityMfApplication.class, args);
	}

}

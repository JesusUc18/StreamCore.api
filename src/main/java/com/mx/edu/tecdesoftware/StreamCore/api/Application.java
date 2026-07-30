package com.mx.edu.tecdesoftware.StreamCore.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "StreamCore API",
				version = "1.0.0",
				description = "API REST para la gestión de una plataforma de streaming: usuarios, planes, suscripciones, contenidos, categorías y visualizaciones."
		),
		security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
		name = "bearerAuth",
		type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT",
		in = SecuritySchemeIn.HEADER
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
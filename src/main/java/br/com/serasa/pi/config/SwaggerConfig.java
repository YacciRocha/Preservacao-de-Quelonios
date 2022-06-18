package br.com.serasa.pi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customizarSwagger() {

		return new OpenAPI().info(new Info().title("Restful API - Projeto Integrador - Preservação de Quelônios")
				.version("v1")
				.description("API desenvolvida como projeto integrador do curso Full Stack Java - DH/Serasa Experian")
				.termsOfService("http://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
}

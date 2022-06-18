package br.com.serasa.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ProjetointegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetointegradorApplication.class, args);
	}
}

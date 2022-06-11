package br.com.serasa.pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import br.com.serasa.pi.domain.entity.Coleta;
import br.com.serasa.pi.domain.entity.Viagem;

@SpringBootApplication
public class ProjetointegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetointegradorApplication.class, args);		
		
	}

}

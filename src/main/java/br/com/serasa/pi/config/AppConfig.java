package br.com.serasa.pi.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class AppConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix="datasource")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}
}

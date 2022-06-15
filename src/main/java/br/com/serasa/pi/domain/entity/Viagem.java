package br.com.serasa.pi.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class Viagem {
	
	@Column(name = "data_viagem")
	private Date dataViagem;
	
	@Column(name = "estado_uf")
	private String estadoUF;	
	
	@Column(name = "municipio")
	private String municipio;	
	
	@Column(name = "comunidade")
	private String comunidade;
}

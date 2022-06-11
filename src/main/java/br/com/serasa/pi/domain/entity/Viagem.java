package br.com.serasa.pi.domain.entity;

import java.util.Date;

import lombok.Data;

@Data
public abstract class Viagem {
	
		
	private Date dataViagem;
	private String estadoUF;	
	private String municipio;	
	private String comunidade;

}

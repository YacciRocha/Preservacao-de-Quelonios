package br.com.serasa.pi.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@MappedSuperclass
public class Viagem {
	
	@Column(name = "data_viagem")
	@NotNull
	private Date dataViagem;
	
	@Column(name = "estado_uf")
	@NotBlank
	@Size(max=2)
	private String estadoUF;	
	
	@Column(name = "municipio")
	@NotBlank
	private String municipio;	
	
	@Column(name = "comunidade")
	@NotBlank
	private String comunidade;
}

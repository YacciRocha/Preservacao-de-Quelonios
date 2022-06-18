package br.com.serasa.pi.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ColetaVO {

	private Integer idColeta;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataViagem;	
	private String estadoUF;		
	private String municipio;		
	private String comunidade;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataColeta;
	private String nomePraiaTabuleiro;
	private Integer numeroCova;
	private Integer quantidadeOvos;
	private String especie;
	private Double distanciaAgua;
	private Double distanciaVegetacao;
	private Float profundidadePrimeiroOvo;
	private Float profundidadeTotal;
	private Float larguraNinho;
	private Float larguraPata;
	private Float larguraEntrePatas;
}

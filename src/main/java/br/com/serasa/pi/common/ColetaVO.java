package br.com.serasa.pi.common;

import java.util.Date;

import lombok.Data;

@Data
public class ColetaVO {

	private Integer idColeta;
	
	private Date dataViagem;
	
	private String estadoUF;	
	
	private String municipio;	
	
	private String comunidade;

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

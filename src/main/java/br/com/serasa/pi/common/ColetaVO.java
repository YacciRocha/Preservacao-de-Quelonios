package br.com.serasa.pi.common;

import java.util.Date;

import lombok.Data;

@Data
public class ColetaVO {

	private Integer idColeta;

	private Date dataColeta;

	private String nomePraiaTabuleiro;

	private Integer numeroCova;

	private Integer quantidadeOvos;

	private String especie;

	private double distanciaAgua;

	private double distanciaVegetacao;

	private float profundidadePrimeiroOvo;

	private float profundidadeTotal;

	private float larguraNinho;

	private float larguraPata;

	private float larguraEntrePatas;
}

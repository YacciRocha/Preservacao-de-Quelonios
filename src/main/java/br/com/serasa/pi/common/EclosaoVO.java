package br.com.serasa.pi.common;

import java.util.Date;

import lombok.Data;

@Data
public class EclosaoVO {

	private Integer idEclosao;	
	private Date dataViagem;
	private String estadoUF;
	private String municipio;
	private String comunidade;
	private String numeroCova;
	private Date dataNascimento;
	private String especie;
	private Integer quantidadeFilhoteVivo;
	private Integer quantidadeOvoInviavel;
	private Integer quantidadeOvoInfertil;
	private Integer quantidadeFilhoteMortoFormiga;
	private Integer quantidadeFilhoteMortoBicheira;
	private Integer quantidadeFilhoteMortoOutros;

}

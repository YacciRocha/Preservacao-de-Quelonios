package br.com.serasa.pi.common;

import java.util.Date;

import lombok.Data;
@Data
public class SolturaVO {

	private Integer idSoltura;
	private Date dataViagem;	
	private String estadoUF;		
	private String municipio;		
	private String comunidade;		
	private String numeroAnimal;
	private String especie;
	private Date dataSoltura;
	private Float carapacaComprimento;
	private Float carapacaLargura;
	private Float plastraoComprimento;
	private Float plastraoLargura;
	private Float peso;
	private Float altura;
}

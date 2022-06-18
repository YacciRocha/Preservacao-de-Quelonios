package br.com.serasa.pi.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
@Data
public class SolturaVO {

	private Integer idSoltura;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataViagem;	
	private String estadoUF;		
	private String municipio;		
	private String comunidade;		
	private String numeroAnimal;
	private String especie;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataSoltura;
	private Float carapacaComprimento;
	private Float carapacaLargura;
	private Float plastraoComprimento;
	private Float plastraoLargura;
	private Float peso;
	private Float altura;
}

package br.com.serasa.pi.common;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SolturaVO extends RepresentationModel<SolturaVO> implements Serializable{
	private static final long serialVersionUID = 1L;

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
	
	@Override
	public int hashCode() {
		return Objects.hash(altura, carapacaComprimento, carapacaLargura, comunidade, dataSoltura, dataViagem, especie,
				estadoUF, idSoltura, municipio, numeroAnimal, peso, plastraoComprimento, plastraoLargura);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolturaVO other = (SolturaVO) obj;
		return Objects.equals(altura, other.altura) && Objects.equals(carapacaComprimento, other.carapacaComprimento)
				&& Objects.equals(carapacaLargura, other.carapacaLargura)
				&& Objects.equals(comunidade, other.comunidade) && Objects.equals(dataSoltura, other.dataSoltura)
				&& Objects.equals(dataViagem, other.dataViagem) && Objects.equals(especie, other.especie)
				&& Objects.equals(estadoUF, other.estadoUF) && Objects.equals(idSoltura, other.idSoltura)
				&& Objects.equals(municipio, other.municipio) && Objects.equals(numeroAnimal, other.numeroAnimal)
				&& Objects.equals(peso, other.peso) && Objects.equals(plastraoComprimento, other.plastraoComprimento)
				&& Objects.equals(plastraoLargura, other.plastraoLargura);
	}
	
	
}

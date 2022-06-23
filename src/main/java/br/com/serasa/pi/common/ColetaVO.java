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
public class ColetaVO extends RepresentationModel<ColetaVO> implements Serializable{
	private static final long serialVersionUID = 1L;

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
	
	@Override
	public int hashCode() {
		return Objects.hash(comunidade, dataColeta, dataViagem, distanciaAgua, distanciaVegetacao, especie, estadoUF,
				idColeta, larguraEntrePatas, larguraNinho, larguraPata, municipio, nomePraiaTabuleiro, numeroCova,
				profundidadePrimeiroOvo, profundidadeTotal, quantidadeOvos);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColetaVO other = (ColetaVO) obj;
		return Objects.equals(comunidade, other.comunidade) && Objects.equals(dataColeta, other.dataColeta)
				&& Objects.equals(dataViagem, other.dataViagem) && Objects.equals(distanciaAgua, other.distanciaAgua)
				&& Objects.equals(distanciaVegetacao, other.distanciaVegetacao)
				&& Objects.equals(especie, other.especie) && Objects.equals(estadoUF, other.estadoUF)
				&& Objects.equals(idColeta, other.idColeta)
				&& Objects.equals(larguraEntrePatas, other.larguraEntrePatas)
				&& Objects.equals(larguraNinho, other.larguraNinho) && Objects.equals(larguraPata, other.larguraPata)
				&& Objects.equals(municipio, other.municipio)
				&& Objects.equals(nomePraiaTabuleiro, other.nomePraiaTabuleiro)
				&& Objects.equals(numeroCova, other.numeroCova)
				&& Objects.equals(profundidadePrimeiroOvo, other.profundidadePrimeiroOvo)
				&& Objects.equals(profundidadeTotal, other.profundidadeTotal)
				&& Objects.equals(quantidadeOvos, other.quantidadeOvos);
	}
	
}

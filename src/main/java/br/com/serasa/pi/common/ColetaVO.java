package br.com.serasa.pi.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColetaVO extends RepresentationModel<ColetaVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idColeta;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataColeta;
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
	private ViagemVO viagem;
	private UsuarioVO voluntario;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataColeta, distanciaAgua, distanciaVegetacao, especie, idColeta,
				larguraEntrePatas, larguraNinho, larguraPata, nomePraiaTabuleiro, numeroCova, profundidadePrimeiroOvo,
				profundidadeTotal, quantidadeOvos, viagem, voluntario);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColetaVO other = (ColetaVO) obj;
		return Objects.equals(dataColeta, other.dataColeta) && Objects.equals(distanciaAgua, other.distanciaAgua)
				&& Objects.equals(distanciaVegetacao, other.distanciaVegetacao)
				&& Objects.equals(especie, other.especie) && Objects.equals(idColeta, other.idColeta)
				&& Objects.equals(larguraEntrePatas, other.larguraEntrePatas)
				&& Objects.equals(larguraNinho, other.larguraNinho) && Objects.equals(larguraPata, other.larguraPata)
				&& Objects.equals(nomePraiaTabuleiro, other.nomePraiaTabuleiro)
				&& Objects.equals(numeroCova, other.numeroCova)
				&& Objects.equals(profundidadePrimeiroOvo, other.profundidadePrimeiroOvo)
				&& Objects.equals(profundidadeTotal, other.profundidadeTotal)
				&& Objects.equals(quantidadeOvos, other.quantidadeOvos) && Objects.equals(viagem, other.viagem)
				&& Objects.equals(voluntario, other.voluntario);
	}
}

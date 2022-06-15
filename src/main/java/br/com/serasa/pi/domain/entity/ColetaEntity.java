package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "coleta")
public class ColetaEntity extends Viagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coleta")
	private Integer idColeta;

	@Column(name = "data_coleta")
	private Date dataColeta;

	@Column(name = "nome_praia_tabuleiro")
	private String nomePraiaTabuleiro;

	@Column(name = "numero_cova")
	private Integer numeroCova;

	@Column(name = "quantidade_ovos")
	private Integer quantidadeOvos;

	@Column(name = "especie")
	private String especie;

	@Column(name = "distancia_agua")
	private Double distanciaAgua;

	@Column(name = "distancia_vegetacao")
	private Double distanciaVegetacao;

	@Column(name = "profundidade_primeiro_ovo")
	private Float profundidadePrimeiroOvo;

	@Column(name = "profundidade_total")
	private Float profundidadeTotal;

	@Column(name = "largura_ninho")
	private Float larguraNinho;

	@Column(name = "largura_patas")
	private Float larguraPata;

	@Column(name = "largura_entre_patas")
	private Float larguraEntrePatas;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColetaEntity other = (ColetaEntity) obj;
		return Objects.equals(dataColeta, other.dataColeta)
				&& Double.doubleToLongBits(distanciaAgua) == Double.doubleToLongBits(other.distanciaAgua)
				&& Double.doubleToLongBits(distanciaVegetacao) == Double.doubleToLongBits(other.distanciaVegetacao)
				&& Objects.equals(especie, other.especie) && Objects.equals(idColeta, other.idColeta)
				&& Float.floatToIntBits(larguraEntrePatas) == Float.floatToIntBits(other.larguraEntrePatas)
				&& Float.floatToIntBits(larguraNinho) == Float.floatToIntBits(other.larguraNinho)
				&& Float.floatToIntBits(larguraPata) == Float.floatToIntBits(other.larguraPata)
				&& Objects.equals(nomePraiaTabuleiro, other.nomePraiaTabuleiro)
				&& Objects.equals(numeroCova, other.numeroCova)
				&& Float.floatToIntBits(profundidadePrimeiroOvo) == Float.floatToIntBits(other.profundidadePrimeiroOvo)
				&& Float.floatToIntBits(profundidadeTotal) == Float.floatToIntBits(other.profundidadeTotal)
				&& Objects.equals(quantidadeOvos, other.quantidadeOvos);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataColeta, distanciaAgua, distanciaVegetacao, especie, idColeta, larguraEntrePatas,
				larguraNinho, larguraPata, nomePraiaTabuleiro, numeroCova, profundidadePrimeiroOvo, profundidadeTotal,
				quantidadeOvos);
	}
}

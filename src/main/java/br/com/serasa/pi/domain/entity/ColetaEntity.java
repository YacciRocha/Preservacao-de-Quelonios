package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "coleta")
public class ColetaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_coleta")
	private Integer idColeta;
	
	@Column(name = "data_coleta")
	@NotNull	
	private LocalDate dataColeta;

	@Column(name = "nome_praia_tabuleiro")
	@NotBlank
	private String nomePraiaTabuleiro;

	@Column(name = "numero_cova")
	@NotNull
	private Integer numeroCova;

	@Column(name = "quantidade_ovos")
	@NotNull
	private Integer quantidadeOvos;

	@Column(name = "especie")
	@NotBlank
	private String especie;

	@Column(name = "distancia_agua")
	@NotNull
	private Double distanciaAgua;

	@Column(name = "distancia_vegetacao")
	@NotNull
	private Double distanciaVegetacao;

	@Column(name = "profundidade_primeiro_ovo")
	@NotNull
	private Float profundidadePrimeiroOvo;

	@Column(name = "profundidade_total")
	@NotNull
	private Float profundidadeTotal;

	@Column(name = "largura_ninho")
	@NotNull
	private Float larguraNinho;

	@Column(name = "largura_patas")
	@NotNull
	private Float larguraPata;

	@Column(name = "largura_entre_patas")
	@NotNull
	private Float larguraEntrePatas;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matriculaVoluntario", referencedColumnName = "matricula")
	private UsuarioEntity voluntario;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idViagem", referencedColumnName = "id_viagem")
	private ViagemEntity viagem;


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

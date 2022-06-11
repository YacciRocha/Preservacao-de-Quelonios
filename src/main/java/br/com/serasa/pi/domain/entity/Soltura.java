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
@Table(name = "viagem_soltura")
public class Soltura extends Viagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_soltura")
	private Integer idSoltura;

	@Column(name = "numero_animal")
	private String numeroAnimal;

	@Column(name = "especie")
	private String especie;

	@Column(name = "data_soltura")
	private Date dataSoltura;

	@Column(name = "carapaca_comprimento")
	private float carapacaComprimento;

	@Column(name = "carapaca_largura")
	private float carapacaLargura;

	@Column(name = "plastrao_comprimento")
	private float plastraoComprimento;

	@Column(name = "plastrao_largura")
	private float plastraoLargura;

	@Column(name = "peso")
	private float peso;

	@Column(name = "altura")
	private float altura;

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
		Soltura other = (Soltura) obj;
		return Float.floatToIntBits(altura) == Float.floatToIntBits(other.altura)
				&& Float.floatToIntBits(carapacaComprimento) == Float.floatToIntBits(other.carapacaComprimento)
				&& Float.floatToIntBits(carapacaLargura) == Float.floatToIntBits(other.carapacaLargura)
				&& Objects.equals(dataSoltura, other.dataSoltura) && Objects.equals(especie, other.especie)
				&& Objects.equals(idSoltura, other.idSoltura) && Objects.equals(numeroAnimal, other.numeroAnimal)
				&& Float.floatToIntBits(peso) == Float.floatToIntBits(other.peso)
				&& Float.floatToIntBits(plastraoComprimento) == Float.floatToIntBits(other.plastraoComprimento)
				&& Float.floatToIntBits(plastraoLargura) == Float.floatToIntBits(other.plastraoLargura);
	}

	@Override
	public int hashCode() {
		return Objects.hash(altura, carapacaComprimento, carapacaLargura, dataSoltura, especie, idSoltura, numeroAnimal,
				peso, plastraoComprimento, plastraoLargura);
	}
}

package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "soltura")
public class SolturaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_soltura")
	private Integer idSoltura;

	@Column(name = "numero_animal")
	@NotBlank
	private String numeroAnimal;

	@Column(name = "especie")
	@NotBlank
	private String especie;

	@Column(name = "data_soltura")
	@NotNull
	private Date dataSoltura;

	@Column(name = "carapaca_comprimento")
	@NotNull
	private Float carapacaComprimento;

	@Column(name = "carapaca_largura")
	@NotNull
	private Float carapacaLargura;

	@Column(name = "plastrao_comprimento")
	@NotNull
	private Float plastraoComprimento;

	@Column(name = "plastrao_largura")
	@NotNull
	private Float plastraoLargura;

	@Column(name = "peso")
	@NotNull
	private Float peso;

	@Column(name = "altura")
	@NotNull
	private Float altura;
	
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
		SolturaEntity other = (SolturaEntity) obj;
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

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "eclosao")
public class EclosaoEntity extends Viagem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_eclosao")	
	private Integer idEclosao;

	@Column(name = "numero_cova")
	@NotBlank
	private String numeroCova;

	@Column(name = "data_nascimento")
	@NotNull
	private Date dataNascimento;

	@Column(name = "especie")
	@NotBlank
	private String especie;

	@Column(name = "quantidade_filhote_vivo")
	@NotNull
	private Integer quantidadeFilhoteVivo;

	@Column(name = "quantidade_ovo_inviavel")
	private Integer quantidadeOvoInviavel;

	@Column(name = "quantidade_ovo_infertil")
	private Integer quantidadeOvoInfertil;

	@Column(name = "quantidade_filhote_morto_formiga")
	private Integer quantidadeFilhoteMortoFormiga;

	@Column(name = "quantidade_filhote_morto_bicheira")
	private Integer quantidadeFilhoteMortoBicheira;

	@Column(name = "quantidade_filhote_morto_outros")
	private Integer quantidadeFilhoteMortoOutros;

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
		EclosaoEntity other = (EclosaoEntity) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(especie, other.especie)
				&& Objects.equals(idEclosao, other.idEclosao) && Objects.equals(numeroCova, other.numeroCova)
				&& Objects.equals(quantidadeFilhoteMortoBicheira, other.quantidadeFilhoteMortoBicheira)
				&& Objects.equals(quantidadeFilhoteMortoFormiga, other.quantidadeFilhoteMortoFormiga)
				&& Objects.equals(quantidadeFilhoteMortoOutros, other.quantidadeFilhoteMortoOutros)
				&& Objects.equals(quantidadeFilhoteVivo, other.quantidadeFilhoteVivo)
				&& Objects.equals(quantidadeOvoInfertil, other.quantidadeOvoInfertil)
				&& Objects.equals(quantidadeOvoInviavel, other.quantidadeOvoInviavel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataNascimento, especie, idEclosao, numeroCova, quantidadeFilhoteMortoBicheira,
				quantidadeFilhoteMortoFormiga, quantidadeFilhoteMortoOutros, quantidadeFilhoteVivo,
				quantidadeOvoInfertil, quantidadeOvoInviavel);
	}
}

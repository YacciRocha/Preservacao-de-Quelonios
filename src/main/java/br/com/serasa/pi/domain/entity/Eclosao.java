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
@Table(name="viagem_eclosao")
public class Eclosao extends Viagem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id_eclosao")
	private Integer idEclosao;	
	
	
	/*
	 * @Column(name = "data_viagem") private Date dataViagem;
	 * 
	 * 
	 * @Column(name = "estado_UF") private String estadoUF;
	 * 
	 * 
	 * @Column(name = "municipio") private String municipio;
	 * 
	 * 
	 * @Column(name = "comunidade") private String comunidade;
	 */

	
	@Column(name = "numero_cova")
	private String numeroCova;	
	
	
	@Column(name = "data_nascimento")
	private Date dataNascimento;	
	
	
	@Column(name = "especie")
	private String especie;

	
	@Column(name = "quantidade_filhote_vivo")
	private Integer quantidadeFilhoteVivo;

	
	@Column(name = "quantidade_ovo_inviavel")
	private Integer quantidadeOvoInviavel ;	

	
	@Column(name = "quantidade_ovo_infertil")
	private Integer  quantidadeOvoInfertil ;

	
	@Column(name = "quantidade_filhote_morto_formiga")
	private Integer  quantidadeFilhoteMortoFormiga;

	
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
		if (getClass() != obj.getClass())
			return false;
		Eclosao other = (Eclosao) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(especie, other.especie)
				&& Objects.equals(idEclosao, other.idEclosao) && Objects.equals(numeroCova, other.numeroCova)
				&& Float.floatToIntBits(quantidadeFilhoteMortoBicheira) == Float
						.floatToIntBits(other.quantidadeFilhoteMortoBicheira)
				&& Double.doubleToLongBits(quantidadeFilhoteMortoFormiga) == Double
						.doubleToLongBits(other.quantidadeFilhoteMortoFormiga)
				&& Float.floatToIntBits(quantidadeFilhoteMortoOutros) == Float
						.floatToIntBits(other.quantidadeFilhoteMortoOutros)
				&& Objects.equals(quantidadeFilhoteVivo, other.quantidadeFilhoteVivo)
				&& Double.doubleToLongBits(quantidadeOvoInfertil) == Double
						.doubleToLongBits(other.quantidadeOvoInfertil)
				&& Objects.equals(quantidadeOvoInviavel, other.quantidadeOvoInviavel);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataNascimento, especie, idEclosao, numeroCova,
				quantidadeFilhoteMortoBicheira, quantidadeFilhoteMortoFormiga, quantidadeFilhoteMortoOutros,
				quantidadeFilhoteVivo, quantidadeOvoInfertil, quantidadeOvoInviavel);
		return result;
	}
	
	

}

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
public class EclosaoVO extends RepresentationModel<EclosaoVO> implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idEclosao;		
	private Integer numeroCova;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	private String especie;
	private Integer quantidadeFilhoteVivo;
	private Integer quantidadeOvoInviavel;
	private Integer quantidadeOvoInfertil;
	private Integer quantidadeFilhoteMortoFormiga;
	private Integer quantidadeFilhoteMortoBicheira;
	private Integer quantidadeFilhoteMortoOutros;
	private ViagemVO viagem;
	private UsuarioVO voluntario;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dataNascimento, especie, idEclosao, numeroCova,
				quantidadeFilhoteMortoBicheira, quantidadeFilhoteMortoFormiga, quantidadeFilhoteMortoOutros,
				quantidadeFilhoteVivo, quantidadeOvoInfertil, quantidadeOvoInviavel, viagem, voluntario);
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
		EclosaoVO other = (EclosaoVO) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(especie, other.especie)
				&& Objects.equals(idEclosao, other.idEclosao) && Objects.equals(numeroCova, other.numeroCova)
				&& Objects.equals(quantidadeFilhoteMortoBicheira, other.quantidadeFilhoteMortoBicheira)
				&& Objects.equals(quantidadeFilhoteMortoFormiga, other.quantidadeFilhoteMortoFormiga)
				&& Objects.equals(quantidadeFilhoteMortoOutros, other.quantidadeFilhoteMortoOutros)
				&& Objects.equals(quantidadeFilhoteVivo, other.quantidadeFilhoteVivo)
				&& Objects.equals(quantidadeOvoInfertil, other.quantidadeOvoInfertil)
				&& Objects.equals(quantidadeOvoInviavel, other.quantidadeOvoInviavel)
				&& Objects.equals(viagem, other.viagem) && Objects.equals(voluntario, other.voluntario);
	}
	
	
	
	

	
	
}

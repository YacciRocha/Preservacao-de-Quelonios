package br.com.serasa.pi.common;

import java.util.Date;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EclosaoVO extends RepresentationModel<EclosaoVO>{

	private Integer idEclosao;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataViagem;
	private String estadoUF;
	private String municipio;
	private String comunidade;
	private String numeroCova;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	private String especie;
	private Integer quantidadeFilhoteVivo;
	private Integer quantidadeOvoInviavel;
	private Integer quantidadeOvoInfertil;
	private Integer quantidadeFilhoteMortoFormiga;
	private Integer quantidadeFilhoteMortoBicheira;
	private Integer quantidadeFilhoteMortoOutros;
	
	@Override
	public int hashCode() {
		return Objects.hash(comunidade, dataNascimento, dataViagem, especie, estadoUF, idEclosao, municipio, numeroCova,
				quantidadeFilhoteMortoBicheira, quantidadeFilhoteMortoFormiga, quantidadeFilhoteMortoOutros,
				quantidadeFilhoteVivo, quantidadeOvoInfertil, quantidadeOvoInviavel);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EclosaoVO other = (EclosaoVO) obj;
		return Objects.equals(comunidade, other.comunidade) && Objects.equals(dataNascimento, other.dataNascimento)
				&& Objects.equals(dataViagem, other.dataViagem) && Objects.equals(especie, other.especie)
				&& Objects.equals(estadoUF, other.estadoUF) && Objects.equals(idEclosao, other.idEclosao)
				&& Objects.equals(municipio, other.municipio) && Objects.equals(numeroCova, other.numeroCova)
				&& Objects.equals(quantidadeFilhoteMortoBicheira, other.quantidadeFilhoteMortoBicheira)
				&& Objects.equals(quantidadeFilhoteMortoFormiga, other.quantidadeFilhoteMortoFormiga)
				&& Objects.equals(quantidadeFilhoteMortoOutros, other.quantidadeFilhoteMortoOutros)
				&& Objects.equals(quantidadeFilhoteVivo, other.quantidadeFilhoteVivo)
				&& Objects.equals(quantidadeOvoInfertil, other.quantidadeOvoInfertil)
				&& Objects.equals(quantidadeOvoInviavel, other.quantidadeOvoInviavel);
	}

	
	
}

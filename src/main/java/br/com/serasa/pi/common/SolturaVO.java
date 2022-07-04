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
public class SolturaVO extends RepresentationModel<SolturaVO> implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer idSoltura;	
	private Integer numeroAnimal;
	private String especie;	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataSoltura;
	private Float carapacaComprimento;
	private Float carapacaLargura;
	private Float plastraoComprimento;
	private Float plastraoLargura;
	private Float peso;
	private Float altura;
	private ViagemVO viagem;
	private UsuarioVO voluntario;

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(altura, carapacaComprimento, carapacaLargura, dataSoltura, especie,
				idSoltura, numeroAnimal, peso, plastraoComprimento, plastraoLargura, viagem, voluntario);
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
		SolturaVO other = (SolturaVO) obj;
		return Objects.equals(altura, other.altura) && Objects.equals(carapacaComprimento, other.carapacaComprimento)
				&& Objects.equals(carapacaLargura, other.carapacaLargura)
				&& Objects.equals(dataSoltura, other.dataSoltura) && Objects.equals(especie, other.especie)
				&& Objects.equals(idSoltura, other.idSoltura) && Objects.equals(numeroAnimal, other.numeroAnimal)
				&& Objects.equals(peso, other.peso) && Objects.equals(plastraoComprimento, other.plastraoComprimento)
				&& Objects.equals(plastraoLargura, other.plastraoLargura) && Objects.equals(viagem, other.viagem)
				&& Objects.equals(voluntario, other.voluntario);
	}
		
}

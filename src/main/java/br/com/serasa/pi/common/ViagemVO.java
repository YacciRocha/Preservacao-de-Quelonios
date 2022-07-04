package br.com.serasa.pi.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ViagemVO extends RepresentationModel<ViagemVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idViagem;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataViagem;	
	private UsuarioVO coordenador;
	private CicloVO idCiclo;
	

	public String getUsuarioVOString() {
		return coordenador.toString();
	}
	public String getCicloVOString() {
		return idCiclo.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViagemVO other = (ViagemVO) obj;
		return Objects.equals(coordenador, other.coordenador) && Objects.equals(dataViagem, other.dataViagem)
				&& Objects.equals(idCiclo, other.idCiclo) && Objects.equals(idViagem, other.idViagem);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(coordenador, dataViagem, idCiclo, idViagem);
		return result;
	}

	
	
}

package br.com.serasa.pi.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.serasa.pi.domain.entity.UsuarioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ViagemVO extends RepresentationModel<ViagemVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idViagem;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataViagem;
	private String estadoUF;
	private String municipio;
	private String comunidade;
	private UsuarioEntity coordenador;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViagemVO other = (ViagemVO) obj;
		return Objects.equals(comunidade, other.comunidade) && Objects.equals(coordenador, other.coordenador)
				&& Objects.equals(dataViagem, other.dataViagem) && Objects.equals(estadoUF, other.estadoUF)
				&& Objects.equals(idViagem, other.idViagem) && Objects.equals(municipio, other.municipio);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(comunidade, coordenador, dataViagem, estadoUF, idViagem, municipio);
		return result;
	}

	

}

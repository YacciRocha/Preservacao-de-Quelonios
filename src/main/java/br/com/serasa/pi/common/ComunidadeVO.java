package br.com.serasa.pi.common;
import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComunidadeVO extends RepresentationModel<ComunidadeVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idComunidade;
	private String nomeComunidade;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idComunidade, nomeComunidade);
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
		ComunidadeVO other = (ComunidadeVO) obj;
		return Objects.equals(idComunidade, other.idComunidade) && Objects.equals(nomeComunidade, other.nomeComunidade);
	}
	
	
}
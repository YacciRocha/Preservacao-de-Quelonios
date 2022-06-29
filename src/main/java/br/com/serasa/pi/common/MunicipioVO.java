package br.com.serasa.pi.common;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MunicipioVO extends RepresentationModel<MunicipioVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer idMunicipio;
	private String nomeMunicipio;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(idMunicipio, nomeMunicipio);
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
		MunicipioVO other = (MunicipioVO) obj;
		return Objects.equals(idMunicipio, other.idMunicipio) && Objects.equals(nomeMunicipio, other.nomeMunicipio);
	}
	
	
}

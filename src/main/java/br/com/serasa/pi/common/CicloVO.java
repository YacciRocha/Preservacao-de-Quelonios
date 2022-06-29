package br.com.serasa.pi.common;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import br.com.serasa.pi.enums.UFEnum;
import lombok.Data;

@Data
public class CicloVO extends RepresentationModel<CicloVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer idCiclo;
	private String nomeCiclo;
	private UFEnum UF;
	private MunicipioVO municipio;
	private ComunidadeVO comunidade;
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CicloVO other = (CicloVO) obj;
		return UF == other.UF && Objects.equals(comunidade, other.comunidade) && Objects.equals(idCiclo, other.idCiclo)
				&& Objects.equals(municipio, other.municipio) && Objects.equals(nomeCiclo, other.nomeCiclo);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(UF, comunidade, idCiclo, municipio, nomeCiclo);
		return result;
	}
	
}

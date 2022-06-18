package br.com.serasa.pi.common;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoluntarioVO extends RepresentationModel<VoluntarioVO> {

	private String matricula;
	private String nome;
	private String email;
	private String senha;
	
	@Override
	public int hashCode() {
		return Objects.hash(email, matricula, nome, senha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoluntarioVO other = (VoluntarioVO) obj;
		return Objects.equals(email, other.email) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha);
	}
	
	

}

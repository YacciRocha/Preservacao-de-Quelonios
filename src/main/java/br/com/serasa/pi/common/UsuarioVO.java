package br.com.serasa.pi.common;

import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioVO extends RepresentationModel<UsuarioVO>{

	private String matricula;
	private String nome;
	private String userName;
	private String password;
	
	@Override
	public int hashCode() {
		return Objects.hash(userName, matricula, nome, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioVO other = (UsuarioVO) obj;
		return Objects.equals(userName, other.userName) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password);
	}

	
}

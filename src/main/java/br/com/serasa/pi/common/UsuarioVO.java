package br.com.serasa.pi.common;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import br.com.serasa.pi.enums.TipoUsuarioEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioVO extends RepresentationModel<UsuarioVO> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String matricula;
	private String nome;
	private String username;
	private String password;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	private Boolean enabled;
	private TipoUsuarioEnum tipoUsuario;

	public UsuarioVO() {
	}

	public UsuarioVO(String matricula, String nome, String userName, String password) {
		this.matricula = matricula;
		this.nome = nome;
		this.username = userName;
		this.password = password;
	}
	
	public UsuarioVO(String matricula, String nome, String username, String password, Boolean accountNonExpired,
			Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, TipoUsuarioEnum tipoUsuario) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.tipoUsuario = tipoUsuario;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(username, matricula, nome, password);
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
		return Objects.equals(username, other.username) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password);
	}
}

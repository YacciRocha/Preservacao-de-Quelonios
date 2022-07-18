package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.serasa.pi.enums.TipoUsuarioEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank
	@Column(name = "matricula", unique = true)
	private String matricula;

	@Column(name = "nome")
	@NotBlank
	private String nome;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipoUsuario", columnDefinition = "enum('ADMIN','COORDENADOR','VOLUNTARIO')")
	private TipoUsuarioEnum tipoUsuario;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao", joinColumns = {
			@JoinColumn(name = "id_usuario", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_permissao", nullable = false) })
	private List<PermissaoEntity> permissions;

	@Column(name = "user_name", unique = true)
	@NotBlank
	@Email
	private String username;

	@NotBlank
	@Column(name = "password")
	private String password;

	@Column(name = "account_non_expired")
	private Boolean accountNonExpired;

	@Column(name = "account_non_locked")
	private Boolean accountNonLocked;

	@Column(name = "credentials_non_expired")
	private Boolean credentialsNonExpired;

	@Column(name = "enabled")
	private Boolean enabled;
	
	public UsuarioEntity() {
		super();
	}
	
	public UsuarioEntity(@NotBlank String matricula, @NotBlank String nome, TipoUsuarioEnum tipoUsuario,
			@NotBlank @Email String username, @NotBlank String password, Boolean accountNonExpired,
			Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled) {
		super();
		this.matricula = matricula;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.username = username;
		this.password = password;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissions;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public List<String> getRoles() {
		List<String> roles = new ArrayList<>();
		for (PermissaoEntity permission : this.permissions) {
			roles.add(permission.getDescription());
		}
		return roles;
	}
}

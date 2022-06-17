package br.com.serasa.pi.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table( name = "voluntario")
public class VoluntarioEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "matricula")
	@NotBlank
	private String matricula;
	
	@Column(name = "nome")
	@NotBlank
	private String nome;
	
	@Column(name = "email")
	@NotBlank
	@Email
	private String email;	
	
	@Column(name = "senha")
	@NotBlank
	private String senha;
		
}

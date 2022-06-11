package br.com.serasa.pi.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table( name = "voluntario")
public class Voluntario {
	
	@Id	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;	
	
	@Column(name = "senha")
	private String senha;
		
}

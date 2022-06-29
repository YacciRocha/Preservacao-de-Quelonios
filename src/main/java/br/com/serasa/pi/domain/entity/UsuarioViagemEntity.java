package br.com.serasa.pi.domain.entity;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario_viagem")
public class UsuarioViagemEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioEntity usuario;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_viagem")
	private ViagemEntity viagem;	
}

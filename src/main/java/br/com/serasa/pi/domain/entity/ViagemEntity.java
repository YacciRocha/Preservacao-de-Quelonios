package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "viagem")
public class ViagemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_viagem")	
	private Integer idViagem;
	
	@Column(name = "data_viagem")	
	private LocalDate dataViagem;
	
	@Column(name = "estado_uf")	
	private String estadoUF;	
	
	@Column(name = "municipio")
	private String municipio;

	@Column(name = "comunidade")	
	private String comunidade;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matriculaCoordenador", referencedColumnName = "matricula")
	private UsuarioEntity coordenador;
}

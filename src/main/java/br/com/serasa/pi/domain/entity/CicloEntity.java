package br.com.serasa.pi.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.serasa.pi.enums.UFEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "ciclo")
public class CicloEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ciclo")	
	private Integer idCiclo;

	@Column(name = "nome_ciclo")	
	private String nomeCiclo;	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "uf", columnDefinition = "enum('AM','PA')")
	private UFEnum UF;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeMunicipioCiclo", referencedColumnName = "nome_municipio")
	private MunicipioEntity municipio;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nomeComunidadeCiclo", referencedColumnName = "nome_comunidade")
	private ComunidadeEntity comunidade;
	
}

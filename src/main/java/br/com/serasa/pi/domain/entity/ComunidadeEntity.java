package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "comunidade")
public class ComunidadeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comunidade")	
	private Integer idComunidade;
	
	@Column(name = "nome_comunidade")
	@NotNull
	private String nomeComunidade;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComunidadeEntity other = (ComunidadeEntity) obj;
		return Objects.equals(idComunidade, other.idComunidade) && Objects.equals(nomeComunidade, other.nomeComunidade);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idComunidade, nomeComunidade);
	}
	
	
}

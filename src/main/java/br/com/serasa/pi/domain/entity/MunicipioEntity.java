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
@Table(name = "municipio")
public class MunicipioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_municipio")	
	private Integer idMunicipio;
	
	@Column(name = "nome_municipio")
	@NotNull
	private String nomeMunicipio;

	@Override
	public int hashCode() {
		return Objects.hash(idMunicipio, nomeMunicipio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MunicipioEntity other = (MunicipioEntity) obj;
		return Objects.equals(idMunicipio, other.idMunicipio) && Objects.equals(nomeMunicipio, other.nomeMunicipio);
	}
	
	
}

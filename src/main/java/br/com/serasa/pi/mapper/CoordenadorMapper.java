package br.com.serasa.pi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.CoordenadorVO;
import br.com.serasa.pi.domain.entity.CoordenadorEntity;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper {
	CoordenadorMapper INSTANCE = Mappers.getMapper(CoordenadorMapper.class);

	CoordenadorVO CoordenadorEntityToCoordenadorVO(CoordenadorEntity coordenador);

	CoordenadorEntity coordenadorVOToCoordenadorEntity(CoordenadorVO coordenadorVO);

	default CoordenadorEntity createCoordenador() {
		return new CoordenadorEntity();
	}
}
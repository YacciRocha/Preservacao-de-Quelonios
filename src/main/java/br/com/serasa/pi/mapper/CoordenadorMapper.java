package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.CoordenadorVO;
import br.com.serasa.pi.domain.entity.CoordenadorEntity;

@Mapper(componentModel = "spring")
public interface CoordenadorMapper {
	CoordenadorMapper INSTANCE = Mappers.getMapper(CoordenadorMapper.class);

	CoordenadorVO coordenadorEntityToCoordenadorVO(CoordenadorEntity coordenadorEntity);

	CoordenadorEntity coordenadorVOToCoordenadorEntity(CoordenadorVO coordenadorVO);

	List<CoordenadorVO> listCoordenadorEntityToListCoordenadoVO(List<CoordenadorEntity> coordenadorEntity);
	
	List<CoordenadorEntity> listCoordenadoVOToListCoordenadorEntity(List<CoordenadorVO> coordenadoVO);
	default  CoordenadorEntity createCoordenadoEntity() {
		return new CoordenadorEntity();
	}
}
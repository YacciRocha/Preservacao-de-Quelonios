package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.ColetaEntity;

@Mapper(componentModel = "spring")
public interface ColetaMapper {
	ColetaMapper INSTANCE = Mappers.getMapper(ColetaMapper.class);

	ColetaVO coletaEntityToColetaVO(ColetaEntity coleta);
	
	ColetaEntity coletaVOToColetaEntity(ColetaVO coletaVO);
	
	List<ColetaVO> listColetaEntityToListColetaVO(List<ColetaEntity> coleta);
	
	List<ColetaEntity> listColetaVOToListColetaEntity(List<ColetaVO> coletaVO);

	default ColetaEntity createColeta() {
		return new ColetaEntity();
	}
}

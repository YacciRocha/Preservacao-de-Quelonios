package br.com.serasa.pi.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.ComunidadeVO;
import br.com.serasa.pi.domain.entity.ComunidadeEntity;

@Mapper(componentModel = "spring")
public interface ComunidadeMapper {
	ComunidadeMapper INSTANCE = Mappers.getMapper(ComunidadeMapper.class);

	ComunidadeVO comunidadeEntityToComunidadeVO(ComunidadeEntity comunidadeEntity);

	ComunidadeEntity comunidadeVOToComunidadeEntity(ComunidadeVO comunidadeVoAtualizacao);

	List<ComunidadeVO> listComunidadeEntityToListComunidadeVO(List<ComunidadeEntity> comunidadeEntity);

	List<ComunidadeEntity> listComunidadeVOToListComunidadeEntity(List<ComunidadeVO> comunidadeVO);

	default ComunidadeEntity createComunidadeEntity() {
		return new ComunidadeEntity();

	}
}
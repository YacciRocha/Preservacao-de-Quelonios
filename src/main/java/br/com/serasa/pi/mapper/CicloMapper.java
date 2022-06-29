package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.CicloVO;
import br.com.serasa.pi.domain.entity.CicloEntity;

@Mapper(componentModel = "spring")
public interface CicloMapper {
	CicloMapper INSTANCE = Mappers.getMapper(CicloMapper.class);

	CicloVO cicloEntityToCicloVO(CicloEntity cicloEntity);

	CicloEntity cicloVOToCicloEntity(CicloVO cicloVoAtualizacao);

	List<CicloVO> listCicloEntityToListCicloVO(List<CicloEntity> cicloEntity);

	List<CicloEntity> listCicloVOToListCicloEntity(List<CicloVO> cicloVO);

	default CicloEntity createCicloEntity() {
		return new CicloEntity();

	}
}
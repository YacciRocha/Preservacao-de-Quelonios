package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.MunicipioVO;
import br.com.serasa.pi.domain.entity.MunicipioEntity;

@Mapper(componentModel = "spring")
public interface MunicipioMapper {
	MunicipioMapper INSTANCE = Mappers.getMapper(MunicipioMapper.class);

	MunicipioVO municipioEntityToMunicipioVO(MunicipioEntity municipioEntity);

	MunicipioEntity municipioVOToMunicipioEntity(MunicipioVO municipioVoAtualizacao);

	List<MunicipioVO> listMunicipioEntityToListMunicipioVO(List<MunicipioEntity> municipioEntity);

	List<MunicipioEntity> listMunicipioVOToListMunicipioEntity(List<MunicipioVO> municipioVO);

	default MunicipioEntity createMunicipioEntity() {
		return new MunicipioEntity();

	}
}
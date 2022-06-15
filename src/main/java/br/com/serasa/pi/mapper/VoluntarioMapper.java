package br.com.serasa.pi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.VoluntarioVO;
import br.com.serasa.pi.domain.entity.VoluntarioEntity;

@Mapper(componentModel = "spring")
public interface VoluntarioMapper {
	VoluntarioMapper INSTANCE = Mappers.getMapper(VoluntarioMapper.class);

	VoluntarioVO voluntarioEntityToVoluntarioVO(VoluntarioEntity voluntario);

	VoluntarioEntity voluntarioVOTovoluntarioEntity(VoluntarioVO voluntarioVO);

	default VoluntarioEntity createVoluntario() {
		return new VoluntarioEntity();
	}
}

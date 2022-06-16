package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.VoluntarioVO;
import br.com.serasa.pi.domain.entity.VoluntarioEntity;

@Mapper(componentModel = "spring")
public interface VoluntarioMapper {
	VoluntarioMapper INSTANCE = Mappers.getMapper(VoluntarioMapper.class);

	VoluntarioVO voluntarioEntityToVoluntarioVO(VoluntarioEntity voluntario);

	VoluntarioEntity voluntarioVOToVoluntarioEntity(VoluntarioVO voluntarioVO);
	
	List<VoluntarioVO> listVoluntarioEntityToListVoluntarioVO(List<VoluntarioEntity> voluntario);
	
	List<VoluntarioEntity> listVoluntarioVOLIstVoluntarioEntity(List<VoluntarioVO> voluntarioVO);
	
	default VoluntarioEntity createVoluntario() {
		return new VoluntarioEntity();
	}
}

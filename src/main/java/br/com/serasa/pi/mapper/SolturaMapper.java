package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.SolturaVO;
import br.com.serasa.pi.domain.entity.SolturaEntity;

@Mapper(componentModel = "spring")
public interface SolturaMapper {
	SolturaMapper INSTANCE = Mappers.getMapper(SolturaMapper.class);

	SolturaVO solturaEntityToSolturaVO(SolturaEntity soltura);
	
	SolturaEntity solturaVOToSolturaEntity(SolturaVO solturaVO);
	
	List<SolturaVO> listSolturaEntityToListSolturaVO(List<SolturaEntity> soltura);
	
	List<SolturaEntity> listSolturaVOToListSolturaEntity(List<SolturaVO> solturaVO);

	default SolturaEntity createSoltura() {
		return new SolturaEntity();
	}
}

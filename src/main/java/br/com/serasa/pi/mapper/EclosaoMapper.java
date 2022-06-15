package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.EclosaoVO;
import br.com.serasa.pi.domain.entity.EclosaoEntity;

@Mapper(componentModel = "spring")
public interface EclosaoMapper {
	EclosaoMapper INSTANCE = Mappers.getMapper(EclosaoMapper.class);

	EclosaoVO eclosaoEntityToEclosaoVO(EclosaoEntity eclosaoEntity);
	
	EclosaoEntity eclosaoVOToEclosaoEntity(EclosaoVO eclosaoVO);
	
	List<EclosaoVO> listEclosaoEntityToListEclosaoVO(List<EclosaoEntity> eclosaoEntity);
	
	List<EclosaoEntity> listEclosaoVOToListEclosaoEntity(List<EclosaoVO> eclosaoVO);

	default EclosaoEntity createEclosaoEntity() {
		return new EclosaoEntity();
	}
}

package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.ViagemVO;
import br.com.serasa.pi.domain.entity.ViagemEntity;

@Mapper(componentModel = "spring")
public interface ViagemMapper {
	ViagemMapper INSTANCE = Mappers.getMapper(ViagemMapper.class);

	ViagemVO viagemEntityToViagemVO(ViagemEntity viagem);
	
	ViagemEntity viagemVOToViagemEntity(ViagemVO viagemVO);
	
	List<ViagemVO> listViagemEntityToListViagemVO(List<ViagemEntity> viagem);
	
	List<ViagemEntity> listViagemVOToListViagemEntity(List<ViagemVO> viagemVO);
	

	default ViagemEntity createviagem() {
		return new ViagemEntity();
	}
}

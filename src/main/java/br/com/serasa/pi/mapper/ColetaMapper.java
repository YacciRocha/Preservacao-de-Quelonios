package br.com.serasa.pi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.Coleta;

@Mapper(componentModel="spring")
public interface ColetaMapper {
	ColetaMapper INSTANCE = Mappers.getMapper( ColetaMapper.class );
    
	ColetaVO coletaToColetaVO(Coleta coleta);
}

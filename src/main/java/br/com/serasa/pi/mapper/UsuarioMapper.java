package br.com.serasa.pi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.domain.entity.UsuarioEntity;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

	UsuarioVO usuarioEntityToUsuarioVO(UsuarioEntity usuarioEntity);

	UsuarioEntity usuarioVOToUsuarioEntity(UsuarioVO usuarioVO);

	List<UsuarioVO> listUsuarioEntityToListUsuarioVO(List<UsuarioEntity> usuarioEntity);
	
	List<UsuarioEntity> listUsuarioVOToListUsuarioEntity(List<UsuarioVO> usuarioVO);
	
	default  UsuarioEntity createUsuarioEntity() {
		return new UsuarioEntity();
	}
}
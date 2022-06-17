package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.VoluntarioVO;
import br.com.serasa.pi.domain.entity.VoluntarioEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.VoluntarioMapper;
import br.com.serasa.pi.repository.VoluntarioRepository;

@Service
public class VoluntarioService {

	@Autowired
	private VoluntarioRepository repository;

	@Autowired
	private VoluntarioMapper voluntarioMapper;

	public VoluntarioVO insert(VoluntarioVO voluntarioVO) {
		VoluntarioEntity voluntarioAInserir = voluntarioMapper.voluntarioVOToVoluntarioEntity(voluntarioVO);
		VoluntarioEntity voluntarioInserido = repository.save(voluntarioAInserir);
		return voluntarioMapper.voluntarioEntityToVoluntarioVO(voluntarioInserido);
	}

	public List<VoluntarioVO> findAll() {
		List<VoluntarioEntity> allVoluntariosEntity = repository.findAll();

		List<VoluntarioVO> retorno = new ArrayList<>();
		if (allVoluntariosEntity != null && !allVoluntariosEntity.isEmpty()) {
			retorno = voluntarioMapper.listVoluntarioEntityToListVoluntarioVO(allVoluntariosEntity);
		}
		return retorno;
	}

	public VoluntarioVO findById(String matricula) {
		var entity = repository.findById(matricula).orElseThrow(() -> new ResourceNotFoundException(matricula));
		VoluntarioVO retorno = voluntarioMapper.voluntarioEntityToVoluntarioVO(entity);
		return retorno;
	}

	public void delete(String matricula) {
		try {
			repository.deleteById(matricula);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(matricula);
		}
	}

	public VoluntarioVO update(String matricula, VoluntarioVO voluntarioVOAtualizacao) {
		try {
			var entity = repository.findById(matricula);
			
			VoluntarioEntity voluntarioEncontrado = entity.get();
			
			voluntarioEncontrado.setNome(voluntarioVOAtualizacao.getNome());
			voluntarioEncontrado.setEmail(voluntarioVOAtualizacao.getEmail());

			VoluntarioEntity voluntarioAtualizado = repository.save(voluntarioEncontrado);
			return voluntarioMapper.voluntarioEntityToVoluntarioVO(voluntarioAtualizado);
			
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(matricula);
		}

	}
}

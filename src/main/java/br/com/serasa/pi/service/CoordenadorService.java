package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.CoordenadorVO;
import br.com.serasa.pi.domain.entity.CoordenadorEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.CoordenadorMapper;
import br.com.serasa.pi.repository.CoordenadorRepository;

@Service
public class CoordenadorService {

	@Autowired
	private CoordenadorRepository repository;

	@Autowired
	private CoordenadorMapper coordenadorMapper;

	public CoordenadorVO insert(CoordenadorVO coordenadorVO) {
		CoordenadorEntity coordenadorAInserir = coordenadorMapper.coordenadorVOToCoordenadorEntity(coordenadorVO);
		CoordenadorEntity coordenadorInserido = repository.save(coordenadorAInserir);
		return coordenadorMapper.coordenadorEntityToCoordenadorVO(coordenadorInserido);

	}

	public List<CoordenadorVO> findAll() {
		List<CoordenadorEntity> allCoordenadoresEntity = repository.findAll();

		List<CoordenadorVO> retorno = new ArrayList<>();
		if (allCoordenadoresEntity != null && !allCoordenadoresEntity.isEmpty()) {
			retorno = coordenadorMapper.listCoordenadorEntityToListCoordenadoVO(allCoordenadoresEntity);
		}
		return retorno;
	}

	public CoordenadorVO findById(String matricula) {
		var entity = repository.findById(matricula).orElseThrow(() -> new ResourceNotFoundException(matricula));
		CoordenadorVO retorno = coordenadorMapper.coordenadorEntityToCoordenadorVO(entity);
		return retorno;
	}

	public void delete(String matricula) {
		try {
			repository.deleteById(matricula);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(matricula);
		}
	}

	public CoordenadorVO update(String matricula, CoordenadorVO coordenadorVoAtualizacao) {
		try {
			var entity = repository.findById(matricula);

			CoordenadorEntity coordenadorEncontrado = entity.get();

			coordenadorEncontrado.setNome(coordenadorVoAtualizacao.getNome());
			coordenadorEncontrado.setEmail(coordenadorVoAtualizacao.getEmail());

			CoordenadorEntity coordenadorAtualizado = repository.save(coordenadorEncontrado);
			return coordenadorMapper.coordenadorEntityToCoordenadorVO(coordenadorAtualizado);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(matricula);
		}

	}
}

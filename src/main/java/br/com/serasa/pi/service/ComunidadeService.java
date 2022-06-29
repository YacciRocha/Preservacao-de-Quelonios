package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ComunidadeVO;
import br.com.serasa.pi.domain.entity.ComunidadeEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.ComunidadeMapper;
import br.com.serasa.pi.repository.ComunidadeRepository;

@Service
public class ComunidadeService {

	@Autowired
	ComunidadeRepository repository;

	@Autowired
	ComunidadeMapper comunidadeMapper;
	

	public ComunidadeVO insert(ComunidadeVO comunidadeVO) {
		ComunidadeEntity comunidadeAInserir = comunidadeMapper.comunidadeVOToComunidadeEntity(comunidadeVO);
		ComunidadeEntity comunidadeInserida = repository.save(comunidadeAInserir);
		return comunidadeMapper.comunidadeEntityToComunidadeVO(comunidadeInserida);
	}

	public List<ComunidadeVO> findAll() {
		List<ComunidadeEntity> allComunidade = repository.findAll();

		List<ComunidadeVO> retorno = new ArrayList<>();
		if (allComunidade != null && !allComunidade.isEmpty()) {
			retorno = comunidadeMapper.listComunidadeEntityToListComunidadeVO(allComunidade);
		}
		return retorno;
	}

	public ComunidadeVO findById(Integer idComunidade) {
		var entity = repository.findById(idComunidade).orElseThrow(() -> new ResourceNotFoundException(idComunidade));
		;
		ComunidadeVO retorno = comunidadeMapper.comunidadeEntityToComunidadeVO(entity);
		return retorno;
	}

	public void delete(Integer idComunidade) {
		try {
			repository.deleteById(idComunidade);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idComunidade);
		}

	}

	public ComunidadeVO update(Integer idComunidade, ComunidadeVO comunidadeVoAtualizacao) {
		try {
var entity = repository.findById(idComunidade);
			
			ComunidadeEntity comunidadeEncontrada = entity.get();
			ComunidadeEntity comunidadeAtualizacao = comunidadeMapper.comunidadeVOToComunidadeEntity(comunidadeVoAtualizacao);

	
			comunidadeEncontrada.setIdComunidade(comunidadeAtualizacao.getIdComunidade());
			comunidadeEncontrada.setNomeComunidade(comunidadeAtualizacao.getNomeComunidade());
			
			ComunidadeEntity comunidadeAtualizada = repository.save(comunidadeEncontrada);
			return comunidadeMapper.comunidadeEntityToComunidadeVO(comunidadeAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idComunidade);
		}
	}
		
	
}

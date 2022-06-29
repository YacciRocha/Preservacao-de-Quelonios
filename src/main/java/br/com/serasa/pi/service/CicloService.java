package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.CicloVO;
import br.com.serasa.pi.domain.entity.CicloEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.CicloMapper;
import br.com.serasa.pi.repository.CicloRepository;

@Service
public class CicloService {

	@Autowired
	CicloRepository repository;

	@Autowired
	CicloMapper cicloMapper;
	

	public CicloVO insert(CicloVO cicloVO) {
		CicloEntity cicloAInserir = cicloMapper.cicloVOToCicloEntity(cicloVO);
		CicloEntity cicloInserida = repository.save(cicloAInserir);
		return cicloMapper.cicloEntityToCicloVO(cicloInserida);
	}

	public List<CicloVO> findAll() {
		List<CicloEntity> allCiclo = repository.findAll();

		List<CicloVO> retorno = new ArrayList<>();
		if (allCiclo != null && !allCiclo.isEmpty()) {
			retorno = cicloMapper.listCicloEntityToListCicloVO(allCiclo);
		}
		return retorno;
	}

	public CicloVO findById(Integer idCiclo) {
		var entity = repository.findById(idCiclo).orElseThrow(() -> new ResourceNotFoundException(idCiclo));
		;
		CicloVO retorno = cicloMapper.cicloEntityToCicloVO(entity);
		return retorno;
	}

	public void delete(Integer idCiclo) {
		try {
			repository.deleteById(idCiclo);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idCiclo);
		}

	}

	public CicloVO update(Integer idCiclo, CicloVO cicloVoAtualizacao) {
		try {
var entity = repository.findById(idCiclo);
			
			CicloEntity cicloEncontrada = entity.get();
			CicloEntity cicloAtualizacao = cicloMapper.cicloVOToCicloEntity(cicloVoAtualizacao);

	
			cicloEncontrada.setIdCiclo(cicloAtualizacao.getIdCiclo());
			cicloEncontrada.setNomeCiclo(cicloAtualizacao.getNomeCiclo());
			cicloEncontrada.setUF(cicloAtualizacao.getUF());
			cicloEncontrada.setMunicipio(cicloAtualizacao.getMunicipio());
			cicloEncontrada.setComunidade(cicloAtualizacao.getComunidade());
			
			
			CicloEntity cicloAtualizada = repository.save(cicloEncontrada);
			return cicloMapper.cicloEntityToCicloVO(cicloAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idCiclo);
		}
	}
		
	
}

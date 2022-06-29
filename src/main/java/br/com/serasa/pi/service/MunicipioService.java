package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.MunicipioVO;
import br.com.serasa.pi.domain.entity.MunicipioEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.MunicipioMapper;
import br.com.serasa.pi.repository.MunicipioRepository;

@Service
public class MunicipioService {

	@Autowired
	MunicipioRepository repository;

	@Autowired
	MunicipioMapper municipioMapper;
	

	public MunicipioVO insert(MunicipioVO municipioVO) {
		MunicipioEntity municipioAInserir = municipioMapper.municipioVOToMunicipioEntity(municipioVO);
		MunicipioEntity municipioInserida = repository.save(municipioAInserir);
		return municipioMapper.municipioEntityToMunicipioVO(municipioInserida);
	}

	public List<MunicipioVO> findAll() {
		List<MunicipioEntity> allMunicipio = repository.findAll();

		List<MunicipioVO> retorno = new ArrayList<>();
		if (allMunicipio != null && !allMunicipio.isEmpty()) {
			retorno = municipioMapper.listMunicipioEntityToListMunicipioVO(allMunicipio);
		}
		return retorno;
	}

	public MunicipioVO findById(Integer idMunicipio) {
		var entity = repository.findById(idMunicipio).orElseThrow(() -> new ResourceNotFoundException(idMunicipio));
		;
		MunicipioVO retorno = municipioMapper.municipioEntityToMunicipioVO(entity);
		return retorno;
	}

	public void delete(Integer idMunicipio) {
		try {
			repository.deleteById(idMunicipio);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idMunicipio);
		}

	}

	public MunicipioVO update(Integer idMunicipio, MunicipioVO municipioVoAtualizacao) {
		try {
var entity = repository.findById(idMunicipio);
			
			MunicipioEntity municipioEncontrada = entity.get();
			MunicipioEntity municipioAtualizacao = municipioMapper.municipioVOToMunicipioEntity(municipioVoAtualizacao);

	
			municipioEncontrada.setIdMunicipio(municipioAtualizacao.getIdMunicipio());
			municipioEncontrada.setNomeMunicipio(municipioAtualizacao.getNomeMunicipio());
			
			MunicipioEntity municipioAtualizada = repository.save(municipioEncontrada);
			return municipioMapper.municipioEntityToMunicipioVO(municipioAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idMunicipio);
		}
	}
		
	
}

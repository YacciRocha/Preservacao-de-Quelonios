package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.SolturaVO;
import br.com.serasa.pi.domain.entity.SolturaEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.SolturaMapper;
import br.com.serasa.pi.repository.SolturaRepository;

@Service
public class SolturaService {

	@Autowired
	SolturaRepository repository;
	@Autowired
	SolturaMapper solturaMapper;

	public SolturaVO insert(SolturaVO solturaVO) {
		SolturaEntity solturaAInserir = solturaMapper.solturaVOToSolturaEntity(solturaVO);
		SolturaEntity solturaInserida = repository.save(solturaAInserir);
		return solturaMapper.solturaEntityToSolturaVO(solturaInserida);
	}

	public List<SolturaVO> findAll() {
		List<SolturaEntity> allSolturas = repository.findAll();

		List<SolturaVO> retorno = new ArrayList<>();
		if (allSolturas != null && !allSolturas.isEmpty()) {
			retorno = solturaMapper.listSolturaEntityToListSolturaVO(allSolturas);
		}
		return retorno;
	}

	public SolturaVO findById(Integer idSoltura) {
		var entity = repository.findById(idSoltura).orElseThrow(() -> new ResourceNotFoundException(idSoltura));
		;
		SolturaVO retorno = solturaMapper.solturaEntityToSolturaVO(entity);
		return retorno;
	}

	public void delete(Integer idSoltura) {
		try {
			repository.deleteById(idSoltura);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idSoltura);
		}

	}

	public SolturaVO update(Integer idSoltura, SolturaVO solturaVoAtualizacao) {
		try {	
			var entity = repository.findById(idSoltura);
		
			SolturaEntity solturaEncontrada = entity.get();
			SolturaEntity solturaAtualizacao = solturaMapper.solturaVOToSolturaEntity(solturaVoAtualizacao);	
		
		solturaEncontrada.setDataViagem(solturaAtualizacao.getDataViagem());		
		solturaEncontrada.setEstadoUF(solturaAtualizacao.getEstadoUF());		
		solturaEncontrada.setMunicipio(solturaAtualizacao.getMunicipio());		
		solturaEncontrada.setComunidade(solturaAtualizacao.getComunidade());
		solturaEncontrada.setNumeroAnimal(solturaAtualizacao.getNumeroAnimal());
		solturaEncontrada.setEspecie(solturaAtualizacao.getEspecie());
		solturaEncontrada.setDataSoltura(solturaAtualizacao.getDataSoltura());
		solturaEncontrada.setCarapacaComprimento(solturaAtualizacao.getCarapacaComprimento());
		solturaEncontrada.setCarapacaLargura(solturaAtualizacao.getCarapacaLargura());
		solturaEncontrada.setPlastraoComprimento(solturaAtualizacao.getPlastraoComprimento());
		solturaEncontrada.setPlastraoLargura(solturaAtualizacao.getPlastraoLargura());
		solturaEncontrada.setPeso(solturaAtualizacao.getPeso());
		solturaEncontrada.setAltura(solturaAtualizacao.getAltura());
		
		
		SolturaEntity solturaAtualizada = repository.save(solturaEncontrada);
		return solturaMapper.solturaEntityToSolturaVO(solturaAtualizada);
		}catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idSoltura);
		}
	}
}

package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.SolturaVO;
import br.com.serasa.pi.domain.entity.SolturaEntity;
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
		Optional<SolturaEntity> optionalSoltura = repository.findById(idSoltura);
		SolturaVO retorno = null;
		if (optionalSoltura.isPresent()) {
			retorno = solturaMapper.solturaEntityToSolturaVO(optionalSoltura.get());
		}
		return retorno;
	}

	public void delete(Integer idSoltura) {
		repository.deleteById(idSoltura);
	}

	public SolturaVO update(Integer idSoltura, SolturaVO solturaVoAtualizacao) {
		Optional<SolturaEntity> optionalSoltura = repository.findById(idSoltura);
		if(optionalSoltura.isPresent()) {
			SolturaEntity solturaEncontrada = optionalSoltura.get();
			SolturaEntity solturaAtualizacao = solturaMapper.solturaVOToSolturaEntity(solturaVoAtualizacao);	
		
		if(solturaAtualizacao.getDataViagem() != null) {
			solturaEncontrada.setDataViagem(solturaAtualizacao.getDataViagem());
		}
		if(solturaAtualizacao.getEstadoUF() != null) {
		solturaEncontrada.setEstadoUF(solturaAtualizacao.getEstadoUF());
		}
		
		if(solturaAtualizacao.getMunicipio() != null) {
		solturaEncontrada.setMunicipio(solturaAtualizacao.getMunicipio());
		}
		
		if(solturaAtualizacao.getComunidade() != null) {
		solturaEncontrada.setComunidade(solturaAtualizacao.getComunidade());
		}
		
		if(solturaAtualizacao.getNumeroAnimal() != null) {
		solturaEncontrada.setNumeroAnimal(solturaAtualizacao.getNumeroAnimal());
		}
		
		if(solturaAtualizacao.getEspecie() != null) {
		solturaEncontrada.setEspecie(solturaAtualizacao.getEspecie());
		}
		
		if(solturaAtualizacao.getDataSoltura() != null) {
		solturaEncontrada.setDataSoltura(solturaAtualizacao.getDataSoltura());
		}
		
		if(solturaAtualizacao.getCarapacaComprimento() != null) {
		solturaEncontrada.setCarapacaComprimento(solturaAtualizacao.getCarapacaComprimento());
		}
		
		if(solturaAtualizacao.getCarapacaLargura() != null) {
		solturaEncontrada.setCarapacaLargura(solturaAtualizacao.getCarapacaLargura());
		}
		
		if(solturaAtualizacao.getPlastraoComprimento() != null) {
		solturaEncontrada.setPlastraoComprimento(solturaAtualizacao.getPlastraoComprimento());
		}
		
		if(solturaAtualizacao.getPlastraoLargura() != null) {
		solturaEncontrada.setPlastraoLargura(solturaAtualizacao.getPlastraoLargura());
		}
		
		if(solturaAtualizacao.getPeso() != null) {
		solturaEncontrada.setPeso(solturaAtualizacao.getPeso());
		}
		
		if(solturaAtualizacao.getAltura() != null) {
		solturaEncontrada.setAltura(solturaAtualizacao.getAltura());
		}
		
		SolturaEntity solturaAtualizada = repository.save(solturaEncontrada);
		return solturaMapper.solturaEntityToSolturaVO(solturaAtualizada);
		}else {
			throw new RuntimeException("Soltura não encontrada");
		}
	}
}

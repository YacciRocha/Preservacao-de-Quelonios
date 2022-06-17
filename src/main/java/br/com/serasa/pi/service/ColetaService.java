package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.ColetaEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.ColetaMapper;
import br.com.serasa.pi.repository.ColetaRepository;

@Service
public class ColetaService {

	@Autowired
	private ColetaRepository repository;
	@Autowired
	private ColetaMapper coletaMapper;

	public ColetaVO insert(ColetaVO coletaVO) {
		ColetaEntity coletaAInserir = coletaMapper.coletaVOToColetaEntity(coletaVO);
		ColetaEntity coletaInserida = repository.save(coletaAInserir);
		return coletaMapper.coletaEntityToColetaVO(coletaInserida);
	}

	public List<ColetaVO> findAll() {
		List<ColetaEntity> allColetas = repository.findAll();

		List<ColetaVO> retorno = new ArrayList<>();
		if (allColetas != null && !allColetas.isEmpty()) {

			retorno = coletaMapper.listColetaEntityToListColetaVO(allColetas);
		}
		return retorno;
	}

	public ColetaVO findById(Integer idColeta) {
		var entity = repository.findById(idColeta).orElseThrow(() -> new ResourceNotFoundException(idColeta));
		ColetaVO retorno = coletaMapper.coletaEntityToColetaVO(entity);
		return retorno;
	}

	public void delete(Integer idColeta) {
		try {
			repository.deleteById(idColeta);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idColeta);
		}

	}

	public ColetaVO update(Integer idColeta, ColetaVO coletaVoAtualizacao) {
		try {
			var entity = repository.findById(idColeta);

			ColetaEntity coletaEncontrada = entity.get();
			ColetaEntity coletaAtualizacao = coletaMapper.coletaVOToColetaEntity(coletaVoAtualizacao);

			coletaEncontrada.setDataViagem(coletaAtualizacao.getDataViagem());
			coletaEncontrada.setEstadoUF(coletaAtualizacao.getEstadoUF());
			coletaEncontrada.setMunicipio(coletaAtualizacao.getMunicipio());
			coletaEncontrada.setComunidade(coletaAtualizacao.getComunidade());
			coletaEncontrada.setDataColeta(coletaAtualizacao.getDataColeta());
			coletaEncontrada.setNomePraiaTabuleiro(coletaAtualizacao.getNomePraiaTabuleiro());
			coletaEncontrada.setNumeroCova(coletaAtualizacao.getNumeroCova());
			coletaEncontrada.setQuantidadeOvos(coletaAtualizacao.getQuantidadeOvos());
			coletaEncontrada.setEspecie(coletaAtualizacao.getEspecie());
			coletaEncontrada.setDistanciaAgua(coletaAtualizacao.getDistanciaAgua());
			coletaEncontrada.setDistanciaVegetacao(coletaAtualizacao.getDistanciaVegetacao());
			coletaEncontrada.setProfundidadePrimeiroOvo(coletaAtualizacao.getProfundidadePrimeiroOvo());
			coletaEncontrada.setProfundidadeTotal(coletaAtualizacao.getProfundidadeTotal());
			coletaEncontrada.setLarguraNinho(coletaAtualizacao.getLarguraNinho());
			coletaEncontrada.setLarguraPata(coletaAtualizacao.getLarguraPata());
			coletaEncontrada.setLarguraEntrePatas(coletaAtualizacao.getLarguraEntrePatas());
			
			ColetaEntity coletaAtualizada = repository.save(coletaEncontrada);
			return coletaMapper.coletaEntityToColetaVO(coletaAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idColeta);
		}
	}
}

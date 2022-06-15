package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.ColetaEntity;
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
		Optional<ColetaEntity> optionalColeta = repository.findById(idColeta);
		ColetaVO retorno = null;
		if (optionalColeta.isPresent()) {
			retorno = coletaMapper.coletaEntityToColetaVO(optionalColeta.get());
		}
		return retorno;
	}

	public void delete(Integer idColeta) {
		repository.deleteById(idColeta);
	}

	public ColetaVO update(Integer idColeta, ColetaVO coletaVoAtualizacao) {
		Optional<ColetaEntity> optionalColeta = repository.findById(idColeta);
		if (optionalColeta.isPresent()) {
			ColetaEntity coletaEncontrada = optionalColeta.get();
			ColetaEntity coletaAtualizacao = coletaMapper.coletaVOToColetaEntity(coletaVoAtualizacao);

			if (coletaAtualizacao.getDataViagem() != null) {
				coletaEncontrada.setDataViagem(coletaAtualizacao.getDataViagem());
			}

			if (coletaAtualizacao.getEstadoUF() != null) {
				coletaEncontrada.setEstadoUF(coletaAtualizacao.getEstadoUF());
			}

			if (coletaAtualizacao.getMunicipio() != null) {
				coletaEncontrada.setMunicipio(coletaAtualizacao.getMunicipio());
			}

			if (coletaAtualizacao.getComunidade() != null) {
				coletaEncontrada.setComunidade(coletaAtualizacao.getComunidade());
			}

			if (coletaAtualizacao.getDataColeta() != null) {
				coletaEncontrada.setDataColeta(coletaAtualizacao.getDataColeta());
			}

			if (coletaAtualizacao.getNomePraiaTabuleiro() != null) {
				coletaEncontrada.setNomePraiaTabuleiro(coletaAtualizacao.getNomePraiaTabuleiro());
			}

			if (coletaAtualizacao.getNumeroCova() != null) {
				coletaEncontrada.setNumeroCova(coletaAtualizacao.getNumeroCova());
			}

			if (coletaAtualizacao.getQuantidadeOvos() != null) {
				coletaEncontrada.setQuantidadeOvos(coletaAtualizacao.getQuantidadeOvos());
			}

			if (coletaAtualizacao.getEspecie() != null) {
				coletaEncontrada.setEspecie(coletaAtualizacao.getEspecie());
			}

			if (coletaAtualizacao.getDistanciaAgua() != null) {
				coletaEncontrada.setDistanciaAgua(coletaAtualizacao.getDistanciaAgua());
			}

			if (coletaAtualizacao.getDistanciaVegetacao() != null) {
				coletaEncontrada.setDistanciaVegetacao(coletaAtualizacao.getDistanciaVegetacao());
			}

			if (coletaAtualizacao.getProfundidadePrimeiroOvo() != null) {
				coletaEncontrada.setProfundidadePrimeiroOvo(coletaAtualizacao.getProfundidadePrimeiroOvo());
			}

			if (coletaAtualizacao.getProfundidadeTotal() != null) {
				coletaEncontrada.setProfundidadeTotal(coletaAtualizacao.getProfundidadeTotal());
			}

			if (coletaAtualizacao.getLarguraNinho() != null) {
				coletaEncontrada.setLarguraNinho(coletaAtualizacao.getLarguraNinho());
			}

			if (coletaAtualizacao.getLarguraPata() != null) {
				coletaEncontrada.setLarguraPata(coletaAtualizacao.getLarguraPata());
			}

			if (coletaAtualizacao.getLarguraEntrePatas() != null) {
				coletaEncontrada.setLarguraEntrePatas(coletaAtualizacao.getLarguraEntrePatas());
			}

			ColetaEntity coletaAtualizada = repository.save(coletaEncontrada);
			return coletaMapper.coletaEntityToColetaVO(coletaAtualizada);
		} else {
			// Lançar exceção
			throw new RuntimeException("Coleta não encontrada");
		}
	}
}

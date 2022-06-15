package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.Coleta;
import br.com.serasa.pi.mapper.ColetaMapper;
import br.com.serasa.pi.repository.ColetaRepository;

@Service
public class ColetaService {

	@Autowired
	private ColetaRepository repository;

	@Autowired
	private ColetaMapper coletaMapper;

	public ColetaVO insert(ColetaVO coletaVO) {
		Coleta coletaAInserir = coletaMapper.coletaVOToColeta(coletaVO);
		Coleta coletaInserida = repository.save(coletaAInserir);
		return coletaMapper.coletaToColetaVO(coletaInserida);
	}

	public List<ColetaVO> findAll() {
		List<Coleta> allColetas = repository.findAll();

		List<ColetaVO> retorno = new ArrayList<>();
		if (allColetas != null && !allColetas.isEmpty()) {

			retorno = coletaMapper.listColetaToListColetaVO(allColetas);

//			Forma com 'for' tradicional...
//			for(int i = 0; i < allColetas.size(); i++) {
//				Coleta coleta = allColetas.get(i);
//				ColetaVO coletaVO = coletaMapper.coletaToColetaVO(coleta);
//				retorno.add(coletaVO);
//			}

//			Foreach...
//			for(Coleta coleta : allColetas) {
//				ColetaVO coletaVO = coletaMapper.coletaToColetaVO(coleta);
//				retorno.add(coletaVO);
//			}

//			Utilizando as Streams....
//			retorno = allColetas.stream().map(
//				coleta -> {
//					return coletaMapper.coletaToColetaVO(coleta);
//				}
//			).collect(Collectors.toList());
		}
		return retorno;
	}

	public ColetaVO findById(Integer idColeta) {
		Optional<Coleta> optionalColeta = repository.findById(idColeta);
		ColetaVO retorno = null;
		if (optionalColeta.isPresent()) {
			retorno = coletaMapper.coletaToColetaVO(optionalColeta.get());
		}
		return retorno;
	}

	public void delete(Integer idColeta) {
		repository.deleteById(idColeta);
	}

	public ColetaVO update(Integer idColeta, ColetaVO coletaVoAtualizacao) {
		Optional<Coleta> optionalColeta = repository.findById(idColeta);
		if (optionalColeta.isPresent()) {
			Coleta coletaEncontrada = optionalColeta.get();
			Coleta coletaAtualizacao = coletaMapper.coletaVOToColeta(coletaVoAtualizacao);

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

			Coleta coletaAtualizada = repository.save(coletaEncontrada);
			return coletaMapper.coletaToColetaVO(coletaAtualizada);
		} else {
			// Lançar exceção
			throw new RuntimeException("Coleta não encontrada");
		}
	}
}

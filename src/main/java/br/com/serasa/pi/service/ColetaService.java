package br.com.serasa.pi.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.ColetaEntity;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.ColetaMapper;
import br.com.serasa.pi.repository.ColetaRepository;
import br.com.serasa.pi.repository.UsuarioRepository;
import br.com.serasa.pi.repository.ViagemRepository;

@Service
public class ColetaService {

	@Autowired
	private ColetaRepository coletaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ViagemRepository viagemRepository;

	@Autowired
	private ColetaMapper coletaMapper;

	public ColetaVO insert(ColetaVO coletaVO) {
		ColetaEntity coletaAInserir = coletaMapper.coletaVOToColetaEntity(coletaVO);

		Optional<UsuarioEntity> optionalVoluntario = usuarioRepository
				.findById(coletaVO.getVoluntario().getMatricula());
		if (optionalVoluntario.isPresent()) {
			coletaAInserir.setVoluntario(optionalVoluntario.get());
		}

		Optional<ViagemEntity> optionalViagem = viagemRepository.findById(coletaVO.getViagem().getIdViagem());
		if (optionalViagem.isPresent()) {
			coletaAInserir.setViagem(optionalViagem.get());
		}

		ColetaEntity coletaInserida = coletaRepository.save(coletaAInserir);
		return coletaMapper.coletaEntityToColetaVO(coletaInserida);
	}

	public Page<ColetaVO> findAll(Pageable pageable) {
		var page = coletaRepository.findAll(pageable);
		return page.map(this::convertToColetaVO);
	}

	public ColetaVO findById(Integer idColeta) {
		var entity = coletaRepository.findById(idColeta).orElseThrow(() -> new ResourceNotFoundException(idColeta));
		ColetaVO retorno = coletaMapper.coletaEntityToColetaVO(entity);
		return retorno;
	}

	public void delete(Integer idColeta) {
		try {
			coletaRepository.deleteById(idColeta);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idColeta);
		}

	}

	public ColetaVO update(Integer idColeta, ColetaVO coletaVoAtualizacao) {
		try {
			var entity = coletaRepository.findById(idColeta);

			ColetaEntity coletaEncontrada = entity.get();
			ColetaEntity coletaAtualizacao = coletaMapper.coletaVOToColetaEntity(coletaVoAtualizacao);

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

			ColetaEntity coletaAtualizada = coletaRepository.save(coletaEncontrada);
			return coletaMapper.coletaEntityToColetaVO(coletaAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idColeta);
		}
	}

	public Page<ColetaVO> findByName(String nomePraiaTabuleiro, Pageable pageable) {
		var page = coletaRepository.findByNomePraiaTabuleiro(nomePraiaTabuleiro, pageable);
		return page.map(this::convertToColetaVO);
	}

	private ColetaVO convertToColetaVO(ColetaEntity entity) {
		return coletaMapper.coletaEntityToColetaVO(entity);
	}

}

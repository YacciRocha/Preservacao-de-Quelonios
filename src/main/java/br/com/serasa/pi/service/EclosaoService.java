package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.EclosaoVO;
import br.com.serasa.pi.domain.entity.EclosaoEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.EclosaoMapper;
import br.com.serasa.pi.repository.EclosaoRepository;

@Service
public class EclosaoService {

	@Autowired
	EclosaoRepository repository;

	@Autowired
	EclosaoMapper eclosaoMapper;

	public EclosaoVO insert(EclosaoVO eclosaoVO) {
		EclosaoEntity eclosaoAInserir = eclosaoMapper.eclosaoVOToEclosaoEntity(eclosaoVO);
		EclosaoEntity eclosaoInserida = repository.save(eclosaoAInserir);
		return eclosaoMapper.eclosaoEntityToEclosaoVO(eclosaoInserida);
	}

	public List<EclosaoVO> findAll() {
		List<EclosaoEntity> allEclosao = repository.findAll();

		List<EclosaoVO> retorno = new ArrayList<>();
		if (allEclosao != null && !allEclosao.isEmpty()) {
			retorno = eclosaoMapper.listEclosaoEntityToListEclosaoVO(allEclosao);
		}
		return retorno;
	}

	public EclosaoVO findById(Integer idEclosao) {
		var entity = repository.findById(idEclosao).orElseThrow(() -> new ResourceNotFoundException(idEclosao));
		;
		EclosaoVO retorno = eclosaoMapper.eclosaoEntityToEclosaoVO(entity);
		return retorno;
	}

	public void delete(Integer idEclosao) {
		try {
			repository.deleteById(idEclosao);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idEclosao);
		}

	}

	public EclosaoVO update(Integer idEclosao, EclosaoVO eclosaoVoAtualizacao) {
		try {
			var entity = repository.findById(idEclosao);
			
			EclosaoEntity eclosaoEncontrada = entity.get();
			EclosaoEntity eclosaoAtualizacao = eclosaoMapper.eclosaoVOToEclosaoEntity(eclosaoVoAtualizacao);

	
			eclosaoEncontrada.setNumeroCova(eclosaoAtualizacao.getNumeroCova());
			eclosaoEncontrada.setDataNascimento(eclosaoAtualizacao.getDataNascimento());
			eclosaoEncontrada.setEspecie(eclosaoAtualizacao.getEspecie());
			eclosaoEncontrada.setQuantidadeFilhoteVivo(eclosaoAtualizacao.getQuantidadeFilhoteVivo());
			eclosaoEncontrada.setQuantidadeOvoInviavel(eclosaoAtualizacao.getQuantidadeOvoInviavel());
			eclosaoEncontrada.setQuantidadeOvoInfertil(eclosaoAtualizacao.getQuantidadeOvoInfertil());
			eclosaoEncontrada.setQuantidadeFilhoteMortoFormiga(eclosaoAtualizacao.getQuantidadeFilhoteMortoFormiga());
			eclosaoEncontrada.setQuantidadeFilhoteMortoBicheira(eclosaoAtualizacao.getQuantidadeFilhoteMortoBicheira());
			eclosaoEncontrada.setQuantidadeFilhoteMortoOutros(eclosaoAtualizacao.getQuantidadeFilhoteMortoOutros());

			EclosaoEntity eclosaoAtualizada = repository.save(eclosaoEncontrada);
			return eclosaoMapper.eclosaoEntityToEclosaoVO(eclosaoAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idEclosao);
		}
	}
}

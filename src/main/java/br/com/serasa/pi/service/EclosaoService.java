package br.com.serasa.pi.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.EclosaoVO;
import br.com.serasa.pi.domain.entity.EclosaoEntity;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.EclosaoMapper;
import br.com.serasa.pi.repository.EclosaoRepository;
import br.com.serasa.pi.repository.UsuarioRepository;
import br.com.serasa.pi.repository.ViagemRepository;

@Service
public class EclosaoService {

	@Autowired
	private EclosaoRepository repository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ViagemRepository viagemRepository;

	@Autowired
	private EclosaoMapper eclosaoMapper;

	public EclosaoVO insert(EclosaoVO eclosaoVO) {
		EclosaoEntity eclosaoAInserir = eclosaoMapper.eclosaoVOToEclosaoEntity(eclosaoVO);
		
		Optional<UsuarioEntity> optionalVoluntario = usuarioRepository
				.findById(eclosaoVO.getVoluntario().getMatricula());
		if (optionalVoluntario.isPresent()) {
			eclosaoAInserir.setVoluntario(optionalVoluntario.get());
		}
		
		Optional<ViagemEntity> optionalViagem = viagemRepository.findById(eclosaoVO.getViagem().getIdViagem());
		if (optionalViagem.isPresent()) {
			eclosaoAInserir.setViagem(optionalViagem.get());
		}
				EclosaoEntity eclosaoInserida = repository.save(eclosaoAInserir);
		return eclosaoMapper.eclosaoEntityToEclosaoVO(eclosaoInserida);
	}

	public Page<EclosaoVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToEclosaoVO);
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
	
	public Page<EclosaoVO> findByNumber(Integer numeroCova, Pageable pageable) {
		var page = repository.findByNumeroCova(numeroCova, pageable);
		return page.map(this::convertToEclosaoVO);
	}
	
	private EclosaoVO convertToEclosaoVO(EclosaoEntity entity) {
		return eclosaoMapper.eclosaoEntityToEclosaoVO(entity);
	}
}

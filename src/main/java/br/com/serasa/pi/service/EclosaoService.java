package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.EclosaoVO;
import br.com.serasa.pi.domain.entity.EclosaoEntity;
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
		if(allEclosao != null && !allEclosao.isEmpty()) {
			retorno = eclosaoMapper.listEclosaoEntityToListEclosaoVO(allEclosao);
		}		
		return  retorno;
	}

	public EclosaoVO findById(Integer idEclosao) {
		Optional<EclosaoEntity> optionalEclosao = repository.findById(idEclosao);
		EclosaoVO retorno = null;
		if(optionalEclosao.isPresent()) {
			retorno = eclosaoMapper.eclosaoEntityToEclosaoVO(optionalEclosao.get());
		}
		return retorno;
	}

	public void delete(Integer idEclosao) {
		repository.deleteById(idEclosao);
	}

	public EclosaoVO update(Integer idEclosao, EclosaoVO eclosaoVoAtualizacao) {
		Optional<EclosaoEntity> optionalEclosao = repository.findById(idEclosao);
		if(optionalEclosao.isPresent()) {
			EclosaoEntity eclosaoEncontrada = optionalEclosao.get();
			EclosaoEntity eclosaoAtualizacao = eclosaoMapper.eclosaoVOToEclosaoEntity(eclosaoVoAtualizacao);
		
		if(eclosaoAtualizacao.getDataViagem() != null) {
			eclosaoEncontrada.setDataViagem(eclosaoAtualizacao.getDataViagem());
		}
		
		if(eclosaoAtualizacao.getEstadoUF() != null) {
		eclosaoEncontrada.setEstadoUF(eclosaoAtualizacao.getEstadoUF());
		}
		
		if(eclosaoAtualizacao.getMunicipio()!= null) {
		eclosaoEncontrada.setMunicipio(eclosaoAtualizacao.getMunicipio());
		}
		
		if(eclosaoAtualizacao.getComunidade()!= null) {
		eclosaoEncontrada.setComunidade(eclosaoAtualizacao.getComunidade());
		}
		
		if(eclosaoAtualizacao.getNumeroCova()!= null) {
		eclosaoEncontrada.setNumeroCova(eclosaoAtualizacao.getNumeroCova());
		}
		
		if(eclosaoAtualizacao.getDataNascimento()!= null) {
		eclosaoEncontrada.setDataNascimento(eclosaoAtualizacao.getDataNascimento());
		}
		
		if(eclosaoAtualizacao.getEspecie()!= null) {
		eclosaoEncontrada.setEspecie(eclosaoAtualizacao.getEspecie());
		}
		
		if(eclosaoAtualizacao.getQuantidadeFilhoteVivo()!= null) {
		eclosaoEncontrada.setQuantidadeFilhoteVivo(eclosaoAtualizacao.getQuantidadeFilhoteVivo());
		}
		
		if(eclosaoAtualizacao.getQuantidadeOvoInviavel()!= null) {
		eclosaoEncontrada.setQuantidadeOvoInviavel(eclosaoAtualizacao.getQuantidadeOvoInviavel());
		}
		
		if(eclosaoAtualizacao.getQuantidadeOvoInfertil()!= null) {
		eclosaoEncontrada.setQuantidadeOvoInfertil(eclosaoAtualizacao.getQuantidadeOvoInfertil());
		}
		
		if(eclosaoAtualizacao.getQuantidadeFilhoteMortoFormiga()!= null) {
		eclosaoEncontrada.setQuantidadeFilhoteMortoFormiga(eclosaoAtualizacao.getQuantidadeFilhoteMortoFormiga());
		}
		
		if(eclosaoAtualizacao.getQuantidadeFilhoteMortoBicheira()!= null) {
		eclosaoEncontrada.setQuantidadeFilhoteMortoBicheira(eclosaoAtualizacao.getQuantidadeFilhoteMortoBicheira());
		}
		
		if(eclosaoAtualizacao.getQuantidadeFilhoteMortoOutros()!= null) {
		eclosaoEncontrada.setQuantidadeFilhoteMortoOutros(eclosaoAtualizacao.getQuantidadeFilhoteMortoOutros());
		}
		

		EclosaoEntity eclosaoAtualizada = repository.save(eclosaoEncontrada);
		return eclosaoMapper.eclosaoEntityToEclosaoVO(eclosaoAtualizada);
		}else {
			throw new RuntimeException("Eclosao não encontrada");
		}
	}
}

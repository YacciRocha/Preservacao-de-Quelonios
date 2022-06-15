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
		if(allColetas != null && !allColetas.isEmpty()) {
			
			// Forma com 'for' tradicional...
			for(int i = 0; i < allColetas.size(); i++) {
				Coleta coleta = allColetas.get(i);
				ColetaVO coletaVO = coletaMapper.coletaToColetaVO(coleta);
				retorno.add(coletaVO);
			}
			
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

	public Coleta findById(Integer idColeta) {
		Optional<Coleta> obj = repository.findById(idColeta);
		return obj.get();
	}

	public void delete(Integer idColeta) {
		repository.deleteById(idColeta);
	}

	public Coleta update(Integer idColeta, Coleta Coleta) {
		Coleta entity = findById(idColeta);
		entity.setDataViagem(Coleta.getDataViagem());
		entity.setEstadoUF(Coleta.getEstadoUF());
		entity.setMunicipio(Coleta.getMunicipio());
		entity.setComunidade(Coleta.getComunidade());
		entity.setDataColeta(Coleta.getDataColeta());
		entity.setNomePraiaTabuleiro(Coleta.getNomePraiaTabuleiro());
		entity.setNumeroCova(Coleta.getNumeroCova());
		entity.setQuantidadeOvos(Coleta.getQuantidadeOvos());
		entity.setEspecie(Coleta.getEspecie());
		entity.setDistanciaAgua(Coleta.getDistanciaAgua());
		entity.setDistanciaVegetacao(Coleta.getDistanciaVegetacao());
		entity.setProfundidadePrimeiroOvo(Coleta.getProfundidadePrimeiroOvo());
		entity.setProfundidadeTotal(Coleta.getProfundidadeTotal());
		entity.setLarguraNinho(Coleta.getLarguraNinho());
		entity.setLarguraPata(Coleta.getLarguraPata());
		entity.setLarguraEntrePatas(Coleta.getLarguraEntrePatas());

		return repository.save(entity);
	}
}

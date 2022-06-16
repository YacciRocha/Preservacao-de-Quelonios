package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.CoordenadorVO;
import br.com.serasa.pi.domain.entity.CoordenadorEntity;
import br.com.serasa.pi.mapper.CoordenadorMapper;
import br.com.serasa.pi.repository.CoordenadorRepository;

@Service
public class CoordenadorService {

	@Autowired
	private CoordenadorRepository repository;

	@Autowired
	private CoordenadorMapper coordenadorMapper;

	public CoordenadorVO insert(CoordenadorVO coordenadorVO) {
		CoordenadorEntity coordenadorAInserir = coordenadorMapper.coordenadorVOToCoordenadorEntity(coordenadorVO);
		CoordenadorEntity coordenadorInserido = repository.save(coordenadorAInserir);
		return coordenadorMapper.coordenadorEntityToCoordenadorVO(coordenadorInserido);
	}

	public List<CoordenadorVO> findAll() {
		List<CoordenadorEntity> allCoordenadoresEntity = repository.findAll();

		List<CoordenadorVO> retorno = new ArrayList<>();
		if (allCoordenadoresEntity != null && !allCoordenadoresEntity.isEmpty()) {
			retorno = coordenadorMapper.listCoordenadorEntityToListCoordenadoVO(allCoordenadoresEntity);
		}
		return retorno;
	}

	public CoordenadorVO findById(String matricula) {
		Optional<CoordenadorEntity> optionalCoordenadorEntity = repository.findById(matricula);
		CoordenadorVO retorno = null;
		if (optionalCoordenadorEntity.isPresent()) {
			retorno = coordenadorMapper.coordenadorEntityToCoordenadorVO(optionalCoordenadorEntity.get());
		}
		return retorno;
	}

	public void delete(String matricula) {
		repository.deleteById(matricula);
	}

	public CoordenadorVO update(String matricula, CoordenadorVO coordenadorVoAtualizacao) {
		Optional<CoordenadorEntity> optionalCoordenadorEntity = repository.findById(matricula);
		if (optionalCoordenadorEntity.isPresent()) {
			CoordenadorEntity coordenadorEncontrado = optionalCoordenadorEntity.get();
			CoordenadorEntity coordenadorAtualizacao = coordenadorMapper
					.coordenadorVOToCoordenadorEntity(coordenadorVoAtualizacao);
			if (coordenadorAtualizacao.getNome() != null) {
				coordenadorEncontrado.setNome(coordenadorAtualizacao.getNome());
			}
			if (coordenadorAtualizacao.getEmail() != null) {
				coordenadorEncontrado.setEmail(coordenadorAtualizacao.getEmail());
			}
			CoordenadorEntity coordenadorAtualizado = repository.save(coordenadorEncontrado);
			return coordenadorMapper.coordenadorEntityToCoordenadorVO(coordenadorAtualizado);
		} else {
			throw new RuntimeException("Coordenador não encontrado");
		}
	}
}

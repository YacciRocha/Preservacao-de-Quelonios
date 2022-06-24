package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ViagemVO;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.ViagemMapper;
import br.com.serasa.pi.repository.ViagemRepository;

@Service
public class ViagemService {
	@Autowired
	private ViagemRepository repository;
	@Autowired
	private ViagemMapper viagemMapper;

	public ViagemVO insert(ViagemVO viagemVO) {
		ViagemEntity viagemAInserir = viagemMapper.viagemVOToViagemEntity(viagemVO);
		ViagemEntity viagemInserida = repository.save(viagemAInserir);
		return viagemMapper.viagemEntityToViagemVO(viagemInserida);
	}

	public List<ViagemVO> findAll() {
		List<ViagemEntity> allViagens = repository.findAll();

		List<ViagemVO> retorno = new ArrayList<>();
		if (allViagens != null && !allViagens.isEmpty()) {

			retorno = viagemMapper.listViagemEntityToListViagemVO(allViagens);
		}
		return retorno;
	}

	public ViagemVO findById(Integer idViagem) {
		var entity = repository.findById(idViagem).orElseThrow(() -> new ResourceNotFoundException(idViagem));
		ViagemVO retorno = viagemMapper.viagemEntityToViagemVO(entity);
		return retorno;
	}

	public void delete(Integer idViagem) {
		try {
			repository.deleteById(idViagem);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idViagem);
		}

	}

	public ViagemVO update(Integer idViagem, ViagemVO viagemVoAtualizacao) {
		try {
			var entity = repository.findById(idViagem);

			ViagemEntity viagemEncontrada = entity.get();
			ViagemEntity viagemAtualizacao = viagemMapper.viagemVOToViagemEntity(viagemVoAtualizacao);

			viagemEncontrada.setDataViagem(viagemAtualizacao.getDataViagem());
			viagemEncontrada.setEstadoUF(viagemAtualizacao.getEstadoUF());
			viagemEncontrada.setMunicipio(viagemAtualizacao.getMunicipio());
			viagemEncontrada.setComunidade(viagemAtualizacao.getComunidade());
			
			ViagemEntity viagemAtualizada = repository.save(viagemEncontrada);
			return viagemMapper.viagemEntityToViagemVO(viagemAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idViagem);
		}
	}

}

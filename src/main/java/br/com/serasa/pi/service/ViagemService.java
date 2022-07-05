package br.com.serasa.pi.service;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.ViagemVO;
import br.com.serasa.pi.domain.entity.CicloEntity;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.ViagemMapper;
import br.com.serasa.pi.repository.CicloRepository;
import br.com.serasa.pi.repository.UsuarioRepository;
import br.com.serasa.pi.repository.ViagemRepository;

@Service
public class ViagemService {
	@Autowired
	private ViagemRepository repository;
	@Autowired
	private ViagemMapper viagemMapper;

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private CicloRepository cicloRepository;

	public ViagemVO insert(ViagemVO viagemVO) {
		ViagemEntity viagemAInserir = viagemMapper.viagemVOToViagemEntity(viagemVO);

		Optional<UsuarioEntity> optionalCoordenador = usuarioRepository
				.findById(viagemVO.getCoordenador().getMatricula());
		if (optionalCoordenador.isPresent()) {
			viagemAInserir.setCoordenador(optionalCoordenador.get());
		}
		Optional<CicloEntity> optionalCiclo = cicloRepository.findById(viagemVO.getIdCiclo().getIdCiclo());
		if (optionalCiclo.isPresent()) {
			viagemAInserir.setIdCiclo(optionalCiclo.get());
		}

		ViagemEntity viagemInserida = repository.save(viagemAInserir);
		return viagemMapper.viagemEntityToViagemVO(viagemInserida);
	}

	public Page<ViagemVO> findAll(Pageable pageable) {
		var page = repository.findAll(pageable);
		return page.map(this::convertToViagemVO);
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
			viagemEncontrada.setIdCiclo(viagemAtualizacao.getIdCiclo());

			ViagemEntity viagemAtualizada = repository.save(viagemEncontrada);
			return viagemMapper.viagemEntityToViagemVO(viagemAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idViagem);
		}
	}

	public Page<ViagemVO> findBydataViagem(LocalDate dataViagem, Pageable pageable) {
		var page = repository.findBydataViagem(dataViagem, pageable);
		return page.map(this::convertToViagemVO);

	}

	private ViagemVO convertToViagemVO(ViagemEntity entity) {
		return viagemMapper.viagemEntityToViagemVO(entity);
	}

}

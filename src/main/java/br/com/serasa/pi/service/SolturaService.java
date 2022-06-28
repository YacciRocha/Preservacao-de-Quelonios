package br.com.serasa.pi.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.SolturaVO;
import br.com.serasa.pi.domain.entity.SolturaEntity;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.SolturaMapper;
import br.com.serasa.pi.repository.SolturaRepository;
import br.com.serasa.pi.repository.UsuarioRepository;
import br.com.serasa.pi.repository.ViagemRepository;

@Service
public class SolturaService {

	@Autowired
	private SolturaRepository solturaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ViagemRepository viagemRepository;
	@Autowired
	private SolturaMapper solturaMapper;

	public SolturaVO insert(SolturaVO solturaVO) {
		SolturaEntity solturaAInserir = solturaMapper.solturaVOToSolturaEntity(solturaVO);

		Optional<UsuarioEntity> optionalVoluntario = usuarioRepository
				.findById(solturaVO.getVoluntario().getMatricula());
		if (optionalVoluntario.isPresent()) {
			solturaAInserir.setVoluntario(optionalVoluntario.get());
		}

		Optional<ViagemEntity> optionalViagem = viagemRepository.findById(solturaVO.getViagem().getIdViagem());
		if (optionalViagem.isPresent()) {
			solturaAInserir.setViagem(optionalViagem.get());

		}

		SolturaEntity solturaInserida = solturaRepository.save(solturaAInserir);
		return solturaMapper.solturaEntityToSolturaVO(solturaInserida);
	}

	public Page<SolturaVO> findAll(Pageable pageable) {
		var page = solturaRepository.findAll(pageable);
		return page.map(this::convertToSolturaVO);
	}

	public SolturaVO findById(Integer idSoltura) {
		var entity = solturaRepository.findById(idSoltura).orElseThrow(() -> new ResourceNotFoundException(idSoltura));
		;
		SolturaVO retorno = solturaMapper.solturaEntityToSolturaVO(entity);
		return retorno;
	}

	public void delete(Integer idSoltura) {
		try {
			solturaRepository.deleteById(idSoltura);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(idSoltura);
		}
	}


	public SolturaVO update(Integer idSoltura, SolturaVO solturaVoAtualizacao) {
		try {
			var entity = solturaRepository.findById(idSoltura);

			SolturaEntity solturaEncontrada = entity.get();
			SolturaEntity solturaAtualizacao = solturaMapper.solturaVOToSolturaEntity(solturaVoAtualizacao);

			solturaEncontrada.setNumeroAnimal(solturaAtualizacao.getNumeroAnimal());
			solturaEncontrada.setEspecie(solturaAtualizacao.getEspecie());
			solturaEncontrada.setDataSoltura(solturaAtualizacao.getDataSoltura());
			solturaEncontrada.setCarapacaComprimento(solturaAtualizacao.getCarapacaComprimento());
			solturaEncontrada.setCarapacaLargura(solturaAtualizacao.getCarapacaLargura());
			solturaEncontrada.setPlastraoComprimento(solturaAtualizacao.getPlastraoComprimento());
			solturaEncontrada.setPlastraoLargura(solturaAtualizacao.getPlastraoLargura());
			solturaEncontrada.setPeso(solturaAtualizacao.getPeso());
			solturaEncontrada.setAltura(solturaAtualizacao.getAltura());

			SolturaEntity solturaAtualizada = solturaRepository.save(solturaEncontrada);
			return solturaMapper.solturaEntityToSolturaVO(solturaAtualizada);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(idSoltura);
		}
	}

	public Page<SolturaVO> findByNumber(Integer numeroAnimal, Pageable pageable) {
		var page = solturaRepository.findByNumeroAnimal(numeroAnimal, pageable);
		return page.map(this::convertToSolturaVO);
	}

	private SolturaVO convertToSolturaVO(SolturaEntity entity) {
		return solturaMapper.solturaEntityToSolturaVO(entity);
	}
}

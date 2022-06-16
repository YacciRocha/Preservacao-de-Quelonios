package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.VoluntarioVO;
import br.com.serasa.pi.domain.entity.VoluntarioEntity;
import br.com.serasa.pi.mapper.VoluntarioMapper;
import br.com.serasa.pi.repository.VoluntarioRepository;

@Service
public class VoluntarioService {

	@Autowired
	private VoluntarioRepository repository;

	@Autowired
	private VoluntarioMapper voluntarioMapper;

	public VoluntarioVO insert(VoluntarioVO voluntarioVO) {
		VoluntarioEntity voluntarioAInserir = voluntarioMapper.voluntarioVOToVoluntarioEntity(voluntarioVO);
		VoluntarioEntity voluntarioInserido = repository.save(voluntarioAInserir);
		return voluntarioMapper.voluntarioEntityToVoluntarioVO(voluntarioInserido);
	}

	public List<VoluntarioVO> findAll() {
		List<VoluntarioEntity> allVoluntariosEntity = repository.findAll();

		List<VoluntarioVO> retorno = new ArrayList<>();
		if (allVoluntariosEntity != null && !allVoluntariosEntity.isEmpty()) {
			retorno = voluntarioMapper.listVoluntarioEntityToListVoluntarioVO(allVoluntariosEntity);
		}
		return retorno;
	}

	public VoluntarioVO findById(String matricula) {
		Optional<VoluntarioEntity> optionalVoluntarioEntity = repository.findById(matricula);
		VoluntarioVO retorno = null;
		if (optionalVoluntarioEntity.isPresent()) {
			retorno = voluntarioMapper.voluntarioEntityToVoluntarioVO(optionalVoluntarioEntity.get());
		}
		return retorno;
	}

	public void delete(String matricula) {
		repository.deleteById(matricula);
	}

	public VoluntarioVO update(String matricula, VoluntarioVO voluntarioVOAtualizacao) {
		Optional<VoluntarioEntity> optionalVoluntarioEntity = repository.findById(matricula);

		if (optionalVoluntarioEntity.isPresent()) {
			VoluntarioEntity voluntarioEncontrado = optionalVoluntarioEntity.get();

			if (voluntarioVOAtualizacao.getNome() != null) {
				voluntarioEncontrado.setNome(voluntarioVOAtualizacao.getNome());
			}
			if (voluntarioVOAtualizacao.getEmail() != null) {
				voluntarioEncontrado.setEmail(voluntarioVOAtualizacao.getNome());

			}
			VoluntarioEntity voluntarioAtualizado = repository.save(voluntarioEncontrado);
			return voluntarioMapper.voluntarioEntityToVoluntarioVO(voluntarioAtualizado);
		} else {
			throw new RuntimeException("Voluntário não encontrado");
		}
	}
}

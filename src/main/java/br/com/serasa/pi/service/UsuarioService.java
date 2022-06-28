package br.com.serasa.pi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.domain.entity.PermissaoEntity;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.mapper.UsuarioMapper;
import br.com.serasa.pi.repository.PermissaoRepository;
import br.com.serasa.pi.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Autowired
	private UsuarioMapper usuarioMapper;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public UsuarioVO insert(UsuarioVO usuarioVO) {
		usuarioVO.setPassword(bCryptPasswordEncoder.encode(usuarioVO.getPassword()));
		UsuarioEntity usuarioAInserir = usuarioMapper.usuarioVOToUsuarioEntity(usuarioVO);
		List<PermissaoEntity> permissoes = new ArrayList<>();
		Optional<PermissaoEntity> optionalPermissao = permissaoRepository.findById(usuarioVO.getTipoUsuario().getCodigoTipoUsuario());
		if(optionalPermissao.isPresent()) {
			permissoes.add(optionalPermissao.get());
		}
		usuarioAInserir.setPermissions(permissoes);
		UsuarioEntity usuarioInserido = usuarioRepository.save(usuarioAInserir);

		return usuarioMapper.usuarioEntityToUsuarioVO(usuarioInserido);
	}

	public List<UsuarioVO> findAll() {
		List<UsuarioEntity> allUsuariosEntity = usuarioRepository.findAll();

		List<UsuarioVO> retorno = new ArrayList<>();
		if (allUsuariosEntity != null && !allUsuariosEntity.isEmpty()) {
			retorno = usuarioMapper.listUsuarioEntityToListUsuarioVO(allUsuariosEntity);
		}
		return retorno;
	}

	public UsuarioVO findById(String matricula) {
		var entity = usuarioRepository.findById(matricula).orElseThrow(() -> new ResourceNotFoundException(matricula));
		UsuarioVO retorno = usuarioMapper.usuarioEntityToUsuarioVO(entity);
		return retorno;
	}

	public void delete(String matricula) {
		try {
			usuarioRepository.deleteById(matricula);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(matricula);
		}
	}

	public UsuarioVO update(String matricula, UsuarioVO usuarioVoAtualizacao) {
		try {
			var entity = usuarioRepository.findById(matricula);

			UsuarioEntity usuarioEncontrado = entity.get();

			usuarioEncontrado.setNome(usuarioVoAtualizacao.getNome());
			usuarioEncontrado.setUsername(usuarioVoAtualizacao.getUsername());

			UsuarioEntity usuarioAtualizado = usuarioRepository.save(usuarioEncontrado);
			return usuarioMapper.usuarioEntityToUsuarioVO(usuarioAtualizado);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException(matricula);
		}
	}

	public UsuarioService(UsuarioRepository repository) {
		this.usuarioRepository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var usuario = usuarioRepository.findByUsername(username);

		if (usuario != null) {
			return usuario;
		} else {
			throw new UsernameNotFoundException("O usuario " + username + " não localizado");
		}
	}
}

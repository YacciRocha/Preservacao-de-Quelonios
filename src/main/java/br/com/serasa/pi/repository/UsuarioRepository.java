package br.com.serasa.pi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {
	
	UsuarioEntity findByUsername(String username);
	
	Page<UsuarioEntity> findByNome(String nome, Pageable peageable);
}



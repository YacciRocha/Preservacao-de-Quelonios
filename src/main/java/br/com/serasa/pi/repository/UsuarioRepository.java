package br.com.serasa.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

	//@Query("SELECT u FROM User u WHERE u.userName =:userName") - em JPQL
	//User findByUserName(@Param("userName")String userName);
	UsuarioEntity findByUserName(String userName);
}



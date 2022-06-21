package br.com.serasa.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	//@Query("SELECT u FROM User u WHERE u.userName =:userName") - em JPQL
	//User findByUserName(@Param("userName")String userName);
	User findByUserName(String userName);
}

package br.com.serasa.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.CoordenadorEntity;

@Repository
public interface CoordenadorRepository extends JpaRepository<CoordenadorEntity, String> {

}

package br.com.serasa.pi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.EclosaoEntity;

@Repository
public interface EclosaoRepository extends JpaRepository<EclosaoEntity, Integer> {
	
	Page<EclosaoEntity> findByNumeroCova(Integer numeroCova, Pageable peageable);
}

package br.com.serasa.pi.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.ViagemEntity;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemEntity, Integer> {
	
	Page<ViagemEntity> findBydataViagem(LocalDate dataViagem, Pageable peageable);

}

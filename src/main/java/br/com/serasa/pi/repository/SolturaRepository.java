package br.com.serasa.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.SolturaEntity;

@Repository
public interface SolturaRepository extends JpaRepository<SolturaEntity, Integer> {

}

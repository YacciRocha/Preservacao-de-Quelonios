package br.com.serasa.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serasa.pi.domain.entity.Coleta;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, Integer> {

}

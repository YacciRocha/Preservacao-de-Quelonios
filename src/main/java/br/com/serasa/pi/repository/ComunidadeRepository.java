package br.com.serasa.pi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.serasa.pi.domain.entity.ComunidadeEntity;

@Repository
public interface ComunidadeRepository extends JpaRepository<ComunidadeEntity, Integer> {

}

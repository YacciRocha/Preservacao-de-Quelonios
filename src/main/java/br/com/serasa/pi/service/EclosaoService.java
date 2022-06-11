package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.entity.Eclosao;
import br.com.serasa.pi.repository.EclosaoRepository;

@Service
public class EclosaoService {
	
	@Autowired
	EclosaoRepository repository;
	
	public Eclosao insert (Eclosao Eclosao) {
		return repository.save(Eclosao);
	}
	
	public List<Eclosao> findAll () {
		return repository.findAll();
	}
	
	public Eclosao findById(Integer idEclosao) {
		Optional<Eclosao> obj = repository.findById(idEclosao);
				return obj.get();
	}
	
	public void delete(Integer idEclosao) {
		repository.deleteById(idEclosao);		
	}
	
	
	public Eclosao update(Integer idEclosao, Eclosao Eclosao) { 
		  Eclosao entity = findById(idEclosao); 
		  entity.setEspecie(Eclosao.getEspecie()); 
		  entity.setComunidade(Eclosao.getComunidade()); 
		  return repository.save(entity); 
		 }		
}

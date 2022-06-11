package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.entity.Soltura;
import br.com.serasa.pi.repository.SolturaRepository;

@Service
public class SolturaService {
	
	@Autowired
	SolturaRepository repository;
	
	public Soltura insert (Soltura Soltura) {
		return repository.save(Soltura);
	}
	
	public List<Soltura> findAll () {
		return repository.findAll();
	}
	
	public Soltura findById(Integer idSoltura) {
		Optional<Soltura> obj = repository.findById(idSoltura);
				return obj.get();
	}
	
	public void delete(Integer idSoltura) {
		repository.deleteById(idSoltura);		
	}
	
	
	public Soltura update(Integer idSoltura, Soltura Soltura) { 
		  Soltura entity = findById(idSoltura); 
		  entity.setDataViagem(Soltura.getDataViagem()); 
		  entity.setComunidade(Soltura.getComunidade()); 
		  return repository.save(entity); 
		 }		
}

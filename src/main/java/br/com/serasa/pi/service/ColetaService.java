package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.entity.Coleta;
import br.com.serasa.pi.repository.ColetaRepository;

@Service
public class ColetaService {
	
	@Autowired
	ColetaRepository repository;
	
	public Coleta insert (Coleta Coleta) {
		return repository.save(Coleta);
	}
	
	public List<Coleta> findAll () {
		return repository.findAll();
	}
	
	public Coleta findById(Integer idColeta) {
		Optional<Coleta> obj = repository.findById(idColeta);
				return obj.get();
	}
	
	public void delete(Integer idColeta) {
		repository.deleteById(idColeta);		
	}
	
	
	public Coleta update(Integer idColeta, Coleta Coleta) { 
		  Coleta entity = findById(idColeta); 
		  entity.setDataViagem(Coleta.getDataViagem()); 
		  entity.setComunidade(Coleta.getComunidade()); 
		  return repository.save(entity); 
		 }		
}

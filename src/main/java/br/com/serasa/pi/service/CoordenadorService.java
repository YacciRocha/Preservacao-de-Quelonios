package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.entity.CoordenadorEntity;
import br.com.serasa.pi.repository.CoordenadorRepository;

@Service
public class CoordenadorService {
	
	@Autowired
	CoordenadorRepository repository;
	
	public CoordenadorEntity insert (CoordenadorEntity coordenador) {
		return repository.save(coordenador);
	}
	
	public List<CoordenadorEntity> findAll () {
		return repository.findAll();
	}
	
	public CoordenadorEntity findById(String matricula) {
		Optional<CoordenadorEntity> obj = repository.findById(matricula);
				return obj.get();
	}
	
	public void delete(String matricula) {
		repository.deleteById(matricula);		
	}
	
	
	public CoordenadorEntity update(String matricula, CoordenadorEntity coordenador) { 
		  CoordenadorEntity entity = findById(matricula); 
		  entity.setNome(coordenador.getNome()); 
		  entity.setEmail(coordenador.getEmail()); 
		  return repository.save(entity); 
		 }		
}

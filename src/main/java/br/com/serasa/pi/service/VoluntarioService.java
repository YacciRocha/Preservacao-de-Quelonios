package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.entity.VoluntarioEntity;
import br.com.serasa.pi.repository.VoluntarioRepository;

@Service
public class VoluntarioService {
	
	@Autowired
	VoluntarioRepository repository;
	
	public VoluntarioEntity insert (VoluntarioEntity Voluntario) {
		return repository.save(Voluntario);
	}
	
	public List<VoluntarioEntity> findAll () {
		return repository.findAll();
	}
	
	public VoluntarioEntity findById(String matricula) {
		Optional<VoluntarioEntity> obj = repository.findById(matricula);
				return obj.get();
	}
	
	public void delete(String matricula) {
		repository.deleteById(matricula);		
	}
	
	
	public VoluntarioEntity update(String matricula, VoluntarioEntity Voluntario) { 
		  VoluntarioEntity entity = findById(matricula); 
		  entity.setNome(Voluntario.getNome()); 
		  entity.setEmail(Voluntario.getEmail()); 
		  return repository.save(entity); 
		 }		
}

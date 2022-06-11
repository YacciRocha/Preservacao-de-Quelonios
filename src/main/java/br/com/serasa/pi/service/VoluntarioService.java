package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.entity.Voluntario;
import br.com.serasa.pi.repository.VoluntarioRepository;

@Service
public class VoluntarioService {
	
	@Autowired
	VoluntarioRepository repository;
	
	public Voluntario insert (Voluntario Voluntario) {
		return repository.save(Voluntario);
	}
	
	public List<Voluntario> findAll () {
		return repository.findAll();
	}
	
	public Voluntario findById(String matricula) {
		Optional<Voluntario> obj = repository.findById(matricula);
				return obj.get();
	}
	
	public void delete(String matricula) {
		repository.deleteById(matricula);		
	}
	
	
	public Voluntario update(String matricula, Voluntario Voluntario) { 
		  Voluntario entity = findById(matricula); 
		  entity.setNome(Voluntario.getNome()); 
		  entity.setEmail(Voluntario.getEmail()); 
		  return repository.save(entity); 
		 }		
}

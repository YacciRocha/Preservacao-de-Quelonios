package br.com.serasa.pi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serasa.pi.domain.Coordenador;
import br.com.serasa.pi.repository.CoordenadorRepository;

@Service
public class CoordenadorService {
	
	@Autowired
	CoordenadorRepository repository;
	
	public Coordenador insert (Coordenador coordenador) {
		return repository.save(coordenador);
	}
	
	public List<Coordenador> findAll () {
		return repository.findAll();
	}
	
	public Coordenador findById(String matricula) {
		Optional<Coordenador> obj = repository.findById(matricula);
				return obj.get();
	}
	
	public void delete(String matricula) {
		repository.deleteById(matricula);		
	}
	
	public Coordenador update(String matricula, Coordenador coordenador) {
		Coordenador entity = findById(coordenador.getMatricula());
		entity.setNome(coordenador.getNome());
		entity.setEmail(coordenador.getEmail());	
		
		
		return repository.save(entity);		
	}
	
}

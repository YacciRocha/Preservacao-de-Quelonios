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
		  entity.setEstadoUF(Soltura.getEstadoUF());
		  entity.setMunicipio(Soltura.getMunicipio()); 
		  entity.setComunidade(Soltura.getComunidade());   
		  entity.setNumeroAnimal(Soltura.getNumeroAnimal()); 
		  entity.setEspecie(Soltura.getEspecie()); 
		  entity.setDataSoltura(Soltura.getDataSoltura()); 
		  entity.setCarapacaComprimento(Soltura.getCarapacaComprimento()); 
		  entity.setCarapacaLargura(Soltura.getCarapacaLargura()); 
		  entity.setPlastraoComprimento(Soltura.getPlastraoComprimento()); 
		  entity.setPlastraoLargura(Soltura.getPlastraoLargura()); 
		  entity.setPeso(Soltura.getPeso()); 
		  entity.setAltura(Soltura.getAltura()); 
		  
		  
		  
		  
		  return repository.save(entity); 
		 }		
}

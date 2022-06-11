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
		  entity.setEstadoUF(Coleta.getEstadoUF());
		  entity.setMunicipio(Coleta.getMunicipio());
		  entity.setComunidade(Coleta.getComunidade());
		  entity.setDataColeta(Coleta.getDataColeta()); 
		  entity.setNomePraiaTabuleiro(Coleta.getNomePraiaTabuleiro()); 
		  entity.setNumeroCova(Coleta.getNumeroCova()); 
		  entity.setQuantidadeOvos(Coleta.getQuantidadeOvos());
		  entity.setEspecie(Coleta.getEspecie());
		  entity.setDistanciaAgua(Coleta.getDistanciaAgua());
		  entity.setDistanciaVegetacao(Coleta.getDistanciaVegetacao());
		  entity.setProfundidadePrimeiroOvo(Coleta.getProfundidadePrimeiroOvo());
		  entity.setProfundidadeTotal(Coleta.getProfundidadeTotal());
		  entity.setLarguraNinho(Coleta.getLarguraNinho());
		  entity.setLarguraPata(Coleta.getLarguraPata());
		  entity.setLarguraEntrePatas(Coleta.getLarguraEntrePatas());		  
		  
		  return repository.save(entity); 
		 }			
}

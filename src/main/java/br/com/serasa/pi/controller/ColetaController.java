package br.com.serasa.pi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.domain.entity.Coleta;
import br.com.serasa.pi.service.ColetaService;

@RestController
@RequestMapping("/coleta")
public class ColetaController {
	
	@Autowired
	private ColetaService coletaService;
	
	@GetMapping
	public ResponseEntity<List<ColetaVO>> findAll() {
		List<ColetaVO> retorno = coletaService.findAll();
		return ResponseEntity.ok().body(retorno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Coleta> findById(@PathVariable("id") Integer idColeta) {
		Coleta obj = coletaService.findById(idColeta);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<ColetaVO> insert(@RequestBody ColetaVO coletaVO) {		
		ColetaVO retorno = coletaService.insert(coletaVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(retorno.getIdColeta()).toUri();
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idColeta) {
		coletaService.delete(idColeta);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Coleta> update(@PathVariable ("id") Integer idColeta, @RequestBody Coleta obj) {
		obj = coletaService.update(idColeta, obj);
		return ResponseEntity.ok().body(obj);
	}
}

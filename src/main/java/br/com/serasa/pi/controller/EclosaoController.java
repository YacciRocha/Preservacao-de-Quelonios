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

import br.com.serasa.pi.domain.entity.Eclosao;
import br.com.serasa.pi.service.EclosaoService;

@RestController
@RequestMapping("/eclosao")
public class EclosaoController {
	
	@Autowired
	EclosaoService service;
	
	@GetMapping
	public ResponseEntity<List<Eclosao>> findAll() {
		List<Eclosao> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Eclosao> findById(@PathVariable("id") Integer idEclosao) {
		Eclosao obj = service.findById(idEclosao);
		return ResponseEntity.ok().body(obj);
				
	}
	
	@PostMapping
	public ResponseEntity<Eclosao> insert(@RequestBody Eclosao obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdEclosao()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idEclosao) {
		service.delete(idEclosao);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Eclosao> update(@PathVariable ("id") Integer idEclosao, @RequestBody Eclosao obj) {
		obj = service.update(idEclosao, obj);
		return ResponseEntity.ok().body(obj);
	}

}

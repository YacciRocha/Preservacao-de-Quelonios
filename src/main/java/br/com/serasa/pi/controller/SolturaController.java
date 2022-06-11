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

import br.com.serasa.pi.domain.entity.Soltura;
import br.com.serasa.pi.service.SolturaService;

@RestController
@RequestMapping("/soltura")
public class SolturaController {
	
	@Autowired
	SolturaService service;
	
	@GetMapping
	public ResponseEntity<List<Soltura>> findAll() {
		List<Soltura> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Soltura> findById(@PathVariable("id") Integer idSoltura) {
		Soltura obj = service.findById(idSoltura);
		return ResponseEntity.ok().body(obj);
				
	}
	
	@PostMapping
	public ResponseEntity<Soltura> insert(@RequestBody Soltura obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getIdSoltura()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idSoltura) {
		service.delete(idSoltura);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Soltura> update(@PathVariable ("id") Integer idSoltura, @RequestBody Soltura obj) {
		obj = service.update(idSoltura, obj);
		return ResponseEntity.ok().body(obj);
	}

}

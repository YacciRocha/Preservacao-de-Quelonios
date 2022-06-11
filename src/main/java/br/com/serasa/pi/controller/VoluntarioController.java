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

import br.com.serasa.pi.domain.entity.Voluntario;
import br.com.serasa.pi.service.VoluntarioService;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {
	
	@Autowired
	VoluntarioService service;
	
	@GetMapping
	public ResponseEntity<List<Voluntario>> findAll() {
		List<Voluntario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<Voluntario> findById(@PathVariable("matricula") String matricula) {
		Voluntario obj = service.findById(matricula);
		return ResponseEntity.ok().body(obj);
				
	}
	
	@PostMapping
	public ResponseEntity<Voluntario> insert(@RequestBody Voluntario obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}")
				.buildAndExpand(obj.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable String matricula) {
		service.delete(matricula);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{matricula}")
	public ResponseEntity<Voluntario> update(@PathVariable String matricula, @RequestBody Voluntario obj) {
		obj = service.update(matricula, obj);
		return ResponseEntity.ok().body(obj);
	}

}

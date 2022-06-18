package br.com.serasa.pi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import br.com.serasa.pi.common.SolturaVO;
import br.com.serasa.pi.service.SolturaService;

@RestController
@RequestMapping("/soltura")
public class SolturaController {
	
	@Autowired
	SolturaService service;	
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<SolturaVO>> findAll() {
		List<SolturaVO> retorno = service.findAll();
		return ResponseEntity.ok().body(retorno);
	}
	
	@GetMapping(value ="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SolturaVO> findById(@PathVariable("id") Integer idSoltura) {
		SolturaVO retorno = service.findById(idSoltura);
		return ResponseEntity.ok().body(retorno);
				
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SolturaVO> insert(@Valid @RequestBody SolturaVO solturaVO) {
		SolturaVO retorno = service.insert(solturaVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(retorno.getIdSoltura()).toUri();
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idSoltura) {
		service.delete(idSoltura);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SolturaVO> update(@Valid @PathVariable ("id") Integer idSoltura, @RequestBody SolturaVO solturaVO) {
		SolturaVO retorno = service.update(idSoltura, solturaVO);
		return ResponseEntity.ok().body(retorno);
	}

}

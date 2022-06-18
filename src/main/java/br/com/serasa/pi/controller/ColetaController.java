package br.com.serasa.pi.controller;

import java.net.URI;
import java.util.List;

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

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.service.ColetaService;

@RestController
@RequestMapping("/coleta")
public class ColetaController {
	
	@Autowired
	private ColetaService coletaService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ColetaVO>> findAll() {
		List<ColetaVO> retorno = coletaService.findAll();
		return ResponseEntity.ok().body(retorno);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> findById(@PathVariable("id") Integer idColeta) {
		ColetaVO retorno = coletaService.findById(idColeta);
		return ResponseEntity.ok().body(retorno);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> insert(@RequestBody ColetaVO coletaVO) {
		ColetaVO retorno = coletaService.insert(coletaVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getIdColeta())
				.toUri();
		return ResponseEntity.created(uri).body(retorno);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer idColeta) {
		coletaService.delete(idColeta);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}",
			 consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> update(@PathVariable("id") Integer idColeta, @RequestBody ColetaVO coletaVO) {
		ColetaVO retorno = coletaService.update(idColeta, coletaVO);
		return ResponseEntity.ok().body(retorno);
	}
}

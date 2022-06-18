package br.com.serasa.pi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.service.ColetaService;

@RestController
@RequestMapping("/coleta")
public class ColetaController {
	
	@Autowired
	private ColetaService coletaService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ColetaVO>> findAll() {
		List<ColetaVO> coletasVO = coletaService.findAll();
		coletasVO.stream().forEach(p->p.add(linkTo(methodOn(ColetaController.class).findById(p.getIdColeta())).withSelfRel()));
		return ResponseEntity.ok().body(coletasVO);
	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> findById(@PathVariable("id") Integer idColeta) {
		ColetaVO coletaVO = coletaService.findById(idColeta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(idColeta)).withSelfRel());
		return ResponseEntity.ok().body(coletaVO);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> insert(@Valid @RequestBody ColetaVO coleta) {
		ColetaVO coletaVO = coletaService.insert(coleta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(coletaVO.getIdColeta())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(coletaVO.getIdColeta())
				.toUri();
		return ResponseEntity.created(uri).body(coletaVO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer idColeta) {
		coletaService.delete(idColeta);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}",
			 consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> update(@Valid @PathVariable("id") Integer idColeta, @RequestBody ColetaVO coleta) {
		ColetaVO coletaVO = coletaService.update(idColeta, coleta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(coletaVO.getIdColeta())).withSelfRel());
		return ResponseEntity.ok().body(coletaVO);
	}
}

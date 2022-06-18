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

import br.com.serasa.pi.common.EclosaoVO;
import br.com.serasa.pi.service.EclosaoService;

@RestController
@RequestMapping("/eclosao")
public class EclosaoController {
	
	@Autowired
	EclosaoService eclosaoService;
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<EclosaoVO>> findAll() {
		List<EclosaoVO> eclosoesVO = eclosaoService.findAll();
		eclosoesVO.stream().forEach(p->p.add(linkTo(methodOn(EclosaoController.class).findById(p.getIdEclosao())).withSelfRel()));
		return ResponseEntity.ok().body(eclosoesVO);
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<EclosaoVO> findById(@PathVariable("id") Integer idEclosao) {
		EclosaoVO eclosaoVO = eclosaoService.findById(idEclosao);
		eclosaoVO.add(linkTo(methodOn(EclosaoController.class).findById(idEclosao)).withSelfRel());
		return ResponseEntity.ok().body(eclosaoVO);
				
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<EclosaoVO> insert(@Valid @RequestBody EclosaoVO eclosao) {
		EclosaoVO eclosaoVO = eclosaoService.insert(eclosao);
		eclosaoVO.add(linkTo(methodOn(EclosaoController.class).findById(eclosaoVO.getIdEclosao())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(eclosaoVO.getIdEclosao()).toUri();
		return ResponseEntity.created(uri).body(eclosaoVO);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idEclosao) {
		eclosaoService.delete(idEclosao);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<EclosaoVO> update(@Valid @PathVariable ("id") Integer idEclosao, @RequestBody EclosaoVO eclosao) {
		EclosaoVO eclosaoVO = eclosaoService.update(idEclosao, eclosao);
		eclosaoVO.add(linkTo(methodOn(EclosaoController.class).findById(eclosaoVO.getIdEclosao())).withSelfRel());
		return ResponseEntity.ok().body(eclosaoVO);
	}

}

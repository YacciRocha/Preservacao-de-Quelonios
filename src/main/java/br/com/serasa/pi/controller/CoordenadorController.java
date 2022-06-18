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

import br.com.serasa.pi.common.CoordenadorVO;
import br.com.serasa.pi.service.CoordenadorService;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {
	
	@Autowired
	private CoordenadorService coordenadorService;	
	
	@GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CoordenadorVO>> findAll() {
		List<CoordenadorVO> coordenadoresVO = coordenadorService.findAll();
		coordenadoresVO.stream().forEach(p->p.add(linkTo(methodOn(CoordenadorController.class).findById(p.getMatricula())).withSelfRel()));
		return ResponseEntity.ok().body(coordenadoresVO);
	}
	
	@GetMapping(value="/{matricula}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CoordenadorVO> findById(@PathVariable("matricula") String matricula) {
		CoordenadorVO coordenadorVO = coordenadorService.findById(matricula);
		coordenadorVO.add(linkTo(methodOn(CoordenadorController.class).findById(matricula)).withSelfRel());
		return ResponseEntity.ok().body(coordenadorVO);				
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CoordenadorVO> insert(@Valid @RequestBody CoordenadorVO coordenador) {
		CoordenadorVO coordenadorVO = coordenadorService.insert(coordenador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}")
				.buildAndExpand(coordenadorVO.getMatricula()).toUri();
		coordenadorVO.add(linkTo(methodOn(CoordenadorController.class).findById(coordenadorVO.getMatricula())).withSelfRel());
		return ResponseEntity.created(uri).body(coordenadorVO);
	}
	
	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable ("matricula") String matricula) {
		coordenadorService.delete(matricula);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{matricula}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CoordenadorVO> update(@Valid @PathVariable ("matricula") String matricula, @RequestBody CoordenadorVO coordenador) {
		CoordenadorVO coordenadorVO = coordenadorService.update(matricula, coordenador);
		coordenadorVO.add(linkTo(methodOn(CoordenadorController.class).findById(coordenadorVO.getMatricula())).withSelfRel());
		return ResponseEntity.ok().body(coordenadorVO);
	}
}

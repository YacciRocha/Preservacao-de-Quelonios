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

import br.com.serasa.pi.common.VoluntarioVO;
import br.com.serasa.pi.service.VoluntarioService;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

	@Autowired
	private VoluntarioService voluntarioService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<VoluntarioVO>> findAll() {
		List<VoluntarioVO> voluntariosVO = voluntarioService.findAll();
		voluntariosVO.stream().forEach(p->p.add(linkTo(methodOn(VoluntarioController.class).findById(p.getMatricula())).withSelfRel()));
		return ResponseEntity.ok().body(voluntariosVO);
	}

	@GetMapping(value = "/{matricula}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<VoluntarioVO> findById(@PathVariable("matricula") String matricula) {
		VoluntarioVO voluntarioVO = voluntarioService.findById(matricula);
		voluntarioVO.add(linkTo(methodOn(VoluntarioController.class).findById(matricula)).withSelfRel());
		return ResponseEntity.ok().body(voluntarioVO);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<VoluntarioVO> insert(@Valid @RequestBody VoluntarioVO voluntario) {
		VoluntarioVO voluntarioVO = voluntarioService.insert(voluntario);
		voluntarioVO.add(linkTo(methodOn(VoluntarioController.class).findById(voluntarioVO.getMatricula())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}")
				.buildAndExpand(voluntarioVO.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(voluntarioVO);
	}

	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula) {
		voluntarioService.delete(matricula);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{matricula}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<VoluntarioVO> update(@Valid @PathVariable("matricula") String matricula,
			@RequestBody VoluntarioVO voluntario) {
		VoluntarioVO voluntarioVO = voluntarioService.update(matricula, voluntario);
		voluntarioVO.add(linkTo(methodOn(VoluntarioController.class).findById(voluntarioVO.getMatricula())).withSelfRel());
		return ResponseEntity.ok().body(voluntarioVO);
	}
}

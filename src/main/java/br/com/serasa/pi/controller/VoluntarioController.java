package br.com.serasa.pi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Voluntário Endpoint")
@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

	@Autowired
	private VoluntarioService voluntarioService;

	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos os Voluntários")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<VoluntarioVO>> findAll() {
		List<VoluntarioVO> voluntariosVO = voluntarioService.findAll();
		voluntariosVO.stream().forEach(p->p.add(linkTo(methodOn(VoluntarioController.class).findById(p.getMatricula())).withSelfRel()));
		return ResponseEntity.ok().body(voluntariosVO);
	}

	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar o Voluntário por id")
	@GetMapping(value = "/{matricula}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<VoluntarioVO> findById(@PathVariable("matricula") String matricula) {
		VoluntarioVO voluntarioVO = voluntarioService.findById(matricula);
		voluntarioVO.add(linkTo(methodOn(VoluntarioController.class).findById(matricula)).withSelfRel());
		return ResponseEntity.ok().body(voluntarioVO);
	}
	
	@Operation(summary="Inserir dados de Voluntário")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<VoluntarioVO> insert(@Valid @RequestBody VoluntarioVO voluntario) {
		VoluntarioVO voluntarioVO = voluntarioService.insert(voluntario);
		voluntarioVO.add(linkTo(methodOn(VoluntarioController.class).findById(voluntarioVO.getMatricula())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}")
				.buildAndExpand(voluntarioVO.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(voluntarioVO);
	}

	@Operation(summary="Deletar dados de Voluntário por id")
	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula) {
		voluntarioService.delete(matricula);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Voluntário por id")
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

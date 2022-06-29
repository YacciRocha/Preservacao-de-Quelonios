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

import br.com.serasa.pi.common.ComunidadeVO;
import br.com.serasa.pi.service.ComunidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Comunidade Endpoint")
@RestController
@RequestMapping("api/comunidade")
public class ComunidadeController {

	@Autowired
	ComunidadeService comunidadeService;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos os Comunidades")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ComunidadeVO>> findAll() {
		List<ComunidadeVO> comunidadesVO = comunidadeService.findAll();
		comunidadesVO.stream().forEach(p->p.add(linkTo(methodOn(ComunidadeController.class).findById(p.getIdComunidade())).withSelfRel()));
		return ResponseEntity.ok().body(comunidadesVO);
	}
	
	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar o Comunidade por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ComunidadeVO> findById(@PathVariable("id") Integer idComunidade) {
		ComunidadeVO comunidadeVO = comunidadeService.findById(idComunidade);
		comunidadeVO.add(linkTo(methodOn(ComunidadeController.class).findById(idComunidade)).withSelfRel());
		return ResponseEntity.ok().body(comunidadeVO);
				
	}
	
	@Operation(summary="Inserir dados de Comunidade")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ComunidadeVO> insert(@Valid @RequestBody ComunidadeVO comunidade) {
		ComunidadeVO comunidadeVO = comunidadeService.insert(comunidade);
		comunidadeVO.add(linkTo(methodOn(ComunidadeController.class).findById(comunidadeVO.getIdComunidade())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(comunidadeVO.getIdComunidade()).toUri();
		return ResponseEntity.created(uri).body(comunidadeVO);
	}
	
	@Operation(summary="Deletar dados de Comunidade por ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idComunidade) {
		comunidadeService.delete(idComunidade);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Comunidade por id")
	@PutMapping(value = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ComunidadeVO> update(@Valid @PathVariable ("id") Integer idComunidade, @RequestBody ComunidadeVO comunidade) {
		ComunidadeVO comunidadeVO = comunidadeService.update(idComunidade, comunidade);
		comunidadeVO.add(linkTo(methodOn(EclosaoController.class).findById(comunidadeVO.getIdComunidade())).withSelfRel());
		return ResponseEntity.ok().body(comunidadeVO);
	}

	
}

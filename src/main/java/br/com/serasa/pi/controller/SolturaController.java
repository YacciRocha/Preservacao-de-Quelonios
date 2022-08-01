package br.com.serasa.pi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serasa.pi.common.SolturaVO;
import br.com.serasa.pi.service.SolturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Soltura Endpoint")
@RestController
@RequestMapping("api/soltura")
public class SolturaController {
	
	@Autowired
	SolturaService solturaService;	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todas as Solturas")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CollectionModel<SolturaVO>> findAll(
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "desc") String direction) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "numeroAnimal"));		
		Page<SolturaVO> eclosoesVO = solturaService.findAll(pageable);
		
		eclosoesVO.stream().forEach(p->p.add(linkTo(methodOn(ColetaController.class).findById(p.getIdSoltura())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(eclosoesVO));
	}
	
	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar a Soltura por id")
	@GetMapping(value ="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SolturaVO> findById(@PathVariable("id") Integer idSoltura) {
		SolturaVO solturaVO = solturaService.findById(idSoltura);
		solturaVO.add(linkTo(methodOn(SolturaController.class).findById(idSoltura)).withSelfRel());
		return ResponseEntity.ok().body(solturaVO);
				
	}
	
	@Operation(summary="Inserir dados de Soltura")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
			     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SolturaVO> insert(@Valid @RequestBody SolturaVO soltura) {
		SolturaVO solturaVO = solturaService.insert(soltura);
		solturaVO.add(linkTo(methodOn(SolturaController.class).findById(solturaVO.getIdSoltura())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(solturaVO.getIdSoltura()).toUri();
		return ResponseEntity.created(uri).body(solturaVO);
	}
	
	@Operation(summary="Deletar dados de Soltura por id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idSoltura) {
		solturaService.delete(idSoltura);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Soltura por id")
	@PutMapping(value = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<SolturaVO> update(@Valid @PathVariable ("id") Integer idSoltura, @RequestBody SolturaVO soltura) {
		SolturaVO solturaVO = solturaService.update(idSoltura, soltura);
		solturaVO.add(linkTo(methodOn(SolturaController.class).findById(solturaVO.getIdSoltura())).withSelfRel());
		return ResponseEntity.ok().body(solturaVO);
	}
	

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Listar soltura por numero do animal")
	@GetMapping(value = "/buscarPorNumeroAnimal/{numeroAnimal}", produces = { "application/json", "application/xml" })
	public ResponseEntity<CollectionModel<SolturaVO>> findSolturaByNumeroAnimal(@PathVariable("numeroAnimal") Integer numeroAnimal,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "numeroAnimal"));
		Page<SolturaVO> solturasVO = solturaService.findByNumber(numeroAnimal, pageable);
		solturasVO.stream()
		.forEach(p->p.add(linkTo(methodOn(ViagemController.class).findById(p.getIdSoltura())).withSelfRel()));
	
	return ResponseEntity.ok(CollectionModel.of(solturasVO));  
	}

}

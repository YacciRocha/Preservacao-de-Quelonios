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

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.service.ColetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Coleta Endpoint")
@RestController
@RequestMapping("api/coleta")
public class ColetaController {

	@Autowired
	private ColetaService coletaService;

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Listar todas as Coletas")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CollectionModel<ColetaVO>> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nomePraiaTabuleiro"));
		Page<ColetaVO> coletasVO = coletaService.findAll(pageable);
		coletasVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(ColetaController.class).findById(p.getIdColeta())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(coletasVO));
	}

	@CrossOrigin({ "localhost:8080", "http://www.preservacaoquelonios.com.br" })
	@Operation(summary = "Listar a Coleta por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> findById(@PathVariable("id") Integer idColeta) {
		ColetaVO coletaVO = coletaService.findById(idColeta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(idColeta)).withSelfRel());
		return ResponseEntity.ok().body(coletaVO);
	}

	@Operation(summary = "Inserir dados de Coleta")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> insert(@Valid @RequestBody ColetaVO coleta) {
		ColetaVO coletaVO = coletaService.insert(coleta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(coletaVO.getIdColeta())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(coletaVO.getIdColeta())
				.toUri();
		return ResponseEntity.created(uri).body(coletaVO);
	}

	@Operation(summary = "Deletar dados de Coleta por id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer idColeta) {
		coletaService.delete(idColeta);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Atualizar dados de Coleta por id")
	@PutMapping(value = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ColetaVO> update(@Valid @PathVariable("id") Integer idColeta, @RequestBody ColetaVO coleta) {
		ColetaVO coletaVO = coletaService.update(idColeta, coleta);
		coletaVO.add(linkTo(methodOn(ColetaController.class).findById(coletaVO.getIdColeta())).withSelfRel());
		return ResponseEntity.ok().body(coletaVO);
	}

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Listar coleta por nome de praia ou tabuleiro")
	@GetMapping(value = "/buscarPorNomePraia/{nomePraiaTabuleiro}", produces = { "application/json",
			"application/xml" })
	public ResponseEntity<CollectionModel<ColetaVO>> findColetaByName(
			@PathVariable("nomePraiaTabuleiro") String nomePraiaTabuleiro,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nomePraiaTabuleiro"));
		Page<ColetaVO> coletasVO = coletaService.findByName(nomePraiaTabuleiro, pageable);
		coletasVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(ViagemController.class).findById(p.getIdColeta())).withSelfRel()));

		return ResponseEntity.ok(CollectionModel.of(coletasVO));
	}

}

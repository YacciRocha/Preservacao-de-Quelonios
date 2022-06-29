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

import br.com.serasa.pi.common.CicloVO;
import br.com.serasa.pi.service.CicloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Ciclo Endpoint")
@RestController
@RequestMapping("api/ciclo")
public class CicloController {

	@Autowired
	CicloService cicloService;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos os Ciclos")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CicloVO>> findAll() {
		List<CicloVO> ciclosVO = cicloService.findAll();
		ciclosVO.stream().forEach(p->p.add(linkTo(methodOn(CicloController.class).findById(p.getIdCiclo())).withSelfRel()));
		return ResponseEntity.ok().body(ciclosVO);
	}
	
	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar os Ciclos por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CicloVO> findById(@PathVariable("id") Integer idCiclo) {
		CicloVO cicloVO = cicloService.findById(idCiclo);
		cicloVO.add(linkTo(methodOn(CicloController.class).findById(idCiclo)).withSelfRel());
		return ResponseEntity.ok().body(cicloVO);
				
	}
	
	@Operation(summary="Inserir dados de Ciclo")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CicloVO> insert(@Valid @RequestBody CicloVO ciclo) {
		CicloVO cicloVO = cicloService.insert(ciclo);
		cicloVO.add(linkTo(methodOn(CicloController.class).findById(cicloVO.getIdCiclo())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cicloVO.getIdCiclo()).toUri();
		return ResponseEntity.created(uri).body(cicloVO);
	}
	
	@Operation(summary="Deletar dados de Ciclo por ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idCiclo) {
		cicloService.delete(idCiclo);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Ciclo por id")
	@PutMapping(value = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CicloVO> update(@Valid @PathVariable ("id") Integer idCiclo, @RequestBody CicloVO ciclo) {
		CicloVO cicloVO = cicloService.update(idCiclo, ciclo);
		cicloVO.add(linkTo(methodOn(EclosaoController.class).findById(cicloVO.getIdCiclo())).withSelfRel());
		return ResponseEntity.ok().body(cicloVO);
	}

	
}


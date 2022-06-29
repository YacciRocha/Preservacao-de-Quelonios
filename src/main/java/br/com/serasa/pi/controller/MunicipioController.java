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

import br.com.serasa.pi.common.MunicipioVO;
import br.com.serasa.pi.service.MunicipioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Municipio Endpoint")
@RestController
@RequestMapping("api/municipio")
public class MunicipioController {

	@Autowired
	MunicipioService municipioService;
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos os Municipios")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<MunicipioVO>> findAll() {
		List<MunicipioVO> municipiosVO = municipioService.findAll();
		municipiosVO.stream().forEach(p->p.add(linkTo(methodOn(MunicipioController.class).findById(p.getIdMunicipio())).withSelfRel()));
		return ResponseEntity.ok().body(municipiosVO);
	}
	
	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar o Municipio por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<MunicipioVO> findById(@PathVariable("id") Integer idMunicipio) {
		MunicipioVO municipioVO = municipioService.findById(idMunicipio);
		municipioVO.add(linkTo(methodOn(MunicipioController.class).findById(idMunicipio)).withSelfRel());
		return ResponseEntity.ok().body(municipioVO);
				
	}
	
	@Operation(summary="Inserir dados de Municipio")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<MunicipioVO> insert(@Valid @RequestBody MunicipioVO municipio) {
		MunicipioVO municipioVO = municipioService.insert(municipio);
		municipioVO.add(linkTo(methodOn(MunicipioController.class).findById(municipioVO.getIdMunicipio())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(municipioVO.getIdMunicipio()).toUri();
		return ResponseEntity.created(uri).body(municipioVO);
	}
	
	@Operation(summary="Deletar dados de Municipio por ID")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idMunicipio) {
		municipioService.delete(idMunicipio);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Municipio por id")
	@PutMapping(value = "/{id}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<MunicipioVO> update(@Valid @PathVariable ("id") Integer idMunicipio, @RequestBody MunicipioVO municipio) {
		MunicipioVO municipioVO = municipioService.update(idMunicipio, municipio);
		municipioVO.add(linkTo(methodOn(EclosaoController.class).findById(municipioVO.getIdMunicipio())).withSelfRel());
		return ResponseEntity.ok().body(municipioVO);
	}

	
}

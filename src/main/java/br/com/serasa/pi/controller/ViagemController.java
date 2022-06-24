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

import br.com.serasa.pi.common.ViagemVO;
import br.com.serasa.pi.service.ViagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Viagem Endpoint")
@RestController
@RequestMapping("api/viagem")
public class ViagemController {

	@Autowired
	private ViagemService viagemService;

	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todas as Viagens")
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<ViagemVO>> findAll() {
		List<ViagemVO> viagensVO = viagemService.findAll();
		viagensVO.stream().forEach(p->p.add(linkTo(methodOn(ViagemController.class).findById(p.getIdViagem())).withSelfRel()));
		return ResponseEntity.ok().body(viagensVO);
	}
	
	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar a Viagem por id")
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ViagemVO> findById(@PathVariable("id") Integer idViagem) {
		ViagemVO viagemVO = viagemService.findById(idViagem);
		viagemVO.add(linkTo(methodOn(ViagemController.class).findById(idViagem)).withSelfRel());
		return ResponseEntity.ok().body(viagemVO);
	}
	@Operation(summary="Inserir dados de Viagem")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ViagemVO> insert(@Valid @RequestBody ViagemVO viagem) {
		ViagemVO viagemVO = viagemService.insert(viagem);
		viagemVO.add(linkTo(methodOn(ViagemController.class).findById(viagemVO.getIdViagem())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(viagemVO.getIdViagem())
				.toUri();
		return ResponseEntity.created(uri).body(viagemVO);
	}	
	
	@Operation(summary="Deletar dados de Viagem por id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer idViagem) {
		viagemService.delete(idViagem);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Viagem por id")
	@PutMapping(value = "/{id}",
			 consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		     produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<ViagemVO> update(@Valid @PathVariable("id") Integer idViagem, @RequestBody ViagemVO viagem) {
		ViagemVO viagemVO = viagemService.update(idViagem, viagem);
		viagemVO.add(linkTo(methodOn(ViagemController.class).findById(viagemVO.getIdViagem())).withSelfRel());
		return ResponseEntity.ok().body(viagemVO);
	}
}

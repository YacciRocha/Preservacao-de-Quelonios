package br.com.serasa.pi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import br.com.serasa.pi.domain.entity.EclosaoEntity;
import br.com.serasa.pi.service.EclosaoService;

@RestController
@RequestMapping("/eclosao")
public class EclosaoController {
	
	@Autowired
	EclosaoService eclosaoService;
	
	@GetMapping
	public ResponseEntity<List<EclosaoVO>> findAll() {
		List<EclosaoVO> retorno = eclosaoService.findAll();
		return ResponseEntity.ok().body(retorno);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EclosaoVO> findById(@PathVariable("id") Integer idEclosao) {
		EclosaoVO retorno = eclosaoService.findById(idEclosao);
		return ResponseEntity.ok().body(retorno);
				
	}
	
	@PostMapping
	public ResponseEntity<EclosaoVO> insert(@RequestBody EclosaoVO eclosaoVO) {
		EclosaoVO retorno = eclosaoService.insert(eclosaoVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(retorno.getIdEclosao()).toUri();
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable ("id") Integer idEclosao) {
		eclosaoService.delete(idEclosao);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EclosaoVO> update(@PathVariable ("id") Integer idEclosao, @RequestBody EclosaoVO eclosaoVO) {
		EclosaoVO retorno = eclosaoService.update(idEclosao, eclosaoVO);
		return ResponseEntity.ok().body(retorno);
	}

}

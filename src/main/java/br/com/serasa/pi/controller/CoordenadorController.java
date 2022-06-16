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

import br.com.serasa.pi.common.CoordenadorVO;
import br.com.serasa.pi.service.CoordenadorService;

@RestController
@RequestMapping("/coordenador")
public class CoordenadorController {
	
	@Autowired
	private CoordenadorService coordenadorService;	
	
	@GetMapping
	public ResponseEntity<List<CoordenadorVO>> findAll() {
		List<CoordenadorVO> retorno = coordenadorService.findAll();
		return ResponseEntity.ok().body(retorno);
	}
	
	@GetMapping("/{matricula}")
	public ResponseEntity<CoordenadorVO> findById(@PathVariable("matricula") String matricula) {
		CoordenadorVO retorno = coordenadorService.findById(matricula);
		return ResponseEntity.ok().body(retorno);				
	}
	
	@PostMapping
	public ResponseEntity<CoordenadorVO> insert(@RequestBody CoordenadorVO coordenadorVO) {
		CoordenadorVO retorno = coordenadorService.insert(coordenadorVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}")
				.buildAndExpand(retorno.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable ("matricula") String matricula) {
		coordenadorService.delete(matricula);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{matricula}")
	public ResponseEntity<CoordenadorVO> update(@PathVariable ("matricula") String matricula, @RequestBody CoordenadorVO coordenadorVO) {
		CoordenadorVO retorno = coordenadorService.update(matricula, coordenadorVO);
		return ResponseEntity.ok().body(retorno);
	}
}

package br.com.serasa.pi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import br.com.serasa.pi.common.VoluntarioVO;
import br.com.serasa.pi.service.VoluntarioService;

@RestController
@RequestMapping("/voluntario")
public class VoluntarioController {

	@Autowired
	private VoluntarioService voluntarioService;

	@GetMapping
	public ResponseEntity<List<VoluntarioVO>> findAll() {
		List<VoluntarioVO> retorno = voluntarioService.findAll();
		return ResponseEntity.ok().body(retorno);
	}

	@GetMapping("/{matricula}")
	public ResponseEntity<VoluntarioVO> findById(@PathVariable("matricula") String matricula) {
		VoluntarioVO retorno = voluntarioService.findById(matricula);
		return ResponseEntity.ok().body(retorno);
	}

	@PostMapping
	public ResponseEntity<VoluntarioVO> insert(@Valid @RequestBody VoluntarioVO voluntarioVO) {
		VoluntarioVO retorno = voluntarioService.insert(voluntarioVO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}")
				.buildAndExpand(retorno.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(retorno);
	}

	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable("matricula") String matricula) {
		voluntarioService.delete(matricula);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{matricula}")
	public ResponseEntity<VoluntarioVO> update(@Valid @PathVariable("matricula") String matricula,
			@RequestBody VoluntarioVO voluntarioVO) {
		VoluntarioVO retorno = voluntarioService.update(matricula, voluntarioVO);
		return ResponseEntity.ok().body(retorno);
	}
}

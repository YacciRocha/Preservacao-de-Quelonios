package br.com.serasa.pi.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serasa.pi.service.RelatoriosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JRException;

@Tag(name = "Relatórios Endpoint")
@RestController
@RequestMapping("api/relatorio")
public class RelatoriosController {

	@Autowired
	private RelatoriosService relatoriosService;

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Gerar relatórios de coleta ")
	@GetMapping(value = "/coleta", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> gerarRelatorioColeta() throws FileNotFoundException, JRException {
		return relatoriosService.exportarRelatorioColeta();
	}

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Gerar relatórios de eclosão ")
	@GetMapping(value = "/eclosao", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> gerarRelatorioEclosao() throws FileNotFoundException, JRException {
		return relatoriosService.exportarRelatorioEclosao();
	}

	@CrossOrigin("localhost:8080")
	@Operation(summary = "Gerar relatórios de soltura ")
	@GetMapping(value = "/soltura", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> gerarRelatorioSoltura() throws FileNotFoundException, JRException {
		return relatoriosService.exportarRelatorioSoltura();
	}

}

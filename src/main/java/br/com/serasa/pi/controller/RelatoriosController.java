package br.com.serasa.pi.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serasa.pi.common.ViagemVO;
import br.com.serasa.pi.service.RelatoriosService;
import br.com.serasa.pi.service.ViagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Tag(name="Relatórios Endpoint")
@RestController
@RequestMapping("api/relatorio")
public class RelatoriosController {
	
	@Autowired
	private RelatoriosService relatoriosService;
	@Autowired
	private ViagemService viagemService;;
	
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary = "Gerar relatórios de viagem ")
	@GetMapping(value = "/report-jasper", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> gerarRelatorioViagem() {

		try {
			List<ViagemVO> viagemList = viagemService.findAll();

			Map<String, Object> listParams = new HashMap<String, Object>();
			listParams.put("titulo", "Usuários");
			listParams.put("usuarios", new JRBeanCollectionDataSource(viagemList));
			
			File file =ResourceUtils.getFile("classpath:templates/coleta-relatorio.jrxml");
			
			JasperPrint jasperPrint =JasperFillManager.fillReport(
						   JasperCompileManager.compileReport(									
											file.getAbsolutePath()) 
							, listParams 
							, new JREmptyDataSource()
					);

			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "usuarios.pdf");
			//create the report in PDF format
			return new ResponseEntity<byte[]>
					(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary = "Gerar relatórios de viagem ")
	@GetMapping(value= "/viagem{format}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> exportarRelatorioViagem() throws FileNotFoundException, JRException {
		return relatoriosService.exportarRelatorioViagem();
	}
	
	@CrossOrigin("localhost:8080")
	@Operation(summary = "Gerar relatórios de coleta ")
	@GetMapping(value= "/coleta{format}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> gerarRelatorioColeta() throws FileNotFoundException, JRException {
		return relatoriosService.exportarRelatorioColeta();
	}

}

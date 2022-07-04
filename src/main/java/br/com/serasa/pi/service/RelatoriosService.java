package br.com.serasa.pi.service;

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
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.serasa.pi.domain.entity.ColetaEntity;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.repository.ColetaRepository;
import br.com.serasa.pi.repository.EclosaoRepository;
import br.com.serasa.pi.repository.SolturaRepository;
import br.com.serasa.pi.repository.ViagemRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatoriosService {
	
	/*
	 * Misturei os dois tipos de desenvolvimento do metodo metade do Rodrigo, metade
	 * do Indiano
	 */
	
	@Autowired
	private ViagemRepository viagemRepository;
	@Autowired
	private ColetaRepository coletaRepository;
	@Autowired
	private EclosaoRepository eclosaoRepository;
	@Autowired
	private SolturaRepository solturaRepository;
	
	//Gera o PDF de Viagens
	public ResponseEntity<byte[]> exportarRelatorioViagem() throws FileNotFoundException, JRException {

		List<ViagemEntity> viagemList = viagemRepository.findAll();

		File file = ResourceUtils.getFile("classpath:templates/Relatorio_Viagem.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(viagemList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("viagem", "Viagens");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		HttpHeaders headers = new HttpHeaders();
		// set the PDF format
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "viagem.pdf");
		// create the report in PDF format
		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
	}
	
	//Gera o PDF de Coleta 
	
	public ResponseEntity<byte[]> exportarRelatorioColeta() throws FileNotFoundException, JRException {

		List<ColetaEntity> coletaList = coletaRepository.findAll();

		File file = ResourceUtils.getFile("classpath:templates/coleta-relatorio.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(coletaList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("coleta", "Coletas");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		HttpHeaders headers = new HttpHeaders();
		// set the PDF format
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "c.pdf");
		// create the report in PDF format
		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
	}
	
	//Gera o PDF de Eclosao
	
	//Gera o PDF de Soltura
	
	//Gera o PDF de Usuario



}

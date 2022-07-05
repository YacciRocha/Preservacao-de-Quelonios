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
import br.com.serasa.pi.domain.entity.EclosaoEntity;
import br.com.serasa.pi.domain.entity.SolturaEntity;
import br.com.serasa.pi.repository.ColetaRepository;
import br.com.serasa.pi.repository.EclosaoRepository;
import br.com.serasa.pi.repository.SolturaRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatoriosService {

	@Autowired
	private ColetaRepository coletaRepository;
	@Autowired
	private EclosaoRepository eclosaoRepository;
	@Autowired
	private SolturaRepository solturaRepository;

	/* Gerador do documento PDF - Coleta */
	public ResponseEntity<byte[]> exportarRelatorioColeta() throws FileNotFoundException, JRException {

		List<ColetaEntity> coletaList = coletaRepository.findAll();

		File file = ResourceUtils.getFile("classpath:templates/coleta-relatorio.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(coletaList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("coleta", "Coletas");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "coleta.pdf");

		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
	}

	/* Gerador do documento PDF - Eclosão */
	public ResponseEntity<byte[]> exportarRelatorioEclosao() throws FileNotFoundException, JRException {

		List<EclosaoEntity> eclosaoList = eclosaoRepository.findAll();

		File file = ResourceUtils.getFile("classpath:templates/eclosao-relatorio.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(eclosaoList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("eclosão", "Eclosões");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "eclosao.pdf");

		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
	}

	/* Gerador do documento PDF - Soltura */
	public ResponseEntity<byte[]> exportarRelatorioSoltura() throws FileNotFoundException, JRException {

		List<SolturaEntity> solturaList = solturaRepository.findAll();

		File file = ResourceUtils.getFile("classpath:templates/soltura-relatorio.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(solturaList);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("soltura", "Solturas");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDispositionFormData("filename", "soltura.pdf");

		return new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jasperPrint), headers, HttpStatus.OK);
	}

}

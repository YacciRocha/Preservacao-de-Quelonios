package br.com.serasa.pi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.common.ViagemVO;
import br.com.serasa.pi.serasa.pdf.MyPdfView;
import br.com.serasa.pi.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Tag(name="Usuario Endpoint")
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
	
	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsuarioService usuarioService;	
	
	@CrossOrigin("localhost:8080")
	@Operation(summary="Listar todos os Usuários")
	@GetMapping (produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<UsuarioVO>> findAll() {
		List<UsuarioVO> usuariosVO = usuarioService.findAll();
		usuariosVO.stream().forEach(p->p.add(linkTo(methodOn(UsuarioController.class).findById(p.getMatricula())).withSelfRel()));
		return ResponseEntity.ok().body(usuariosVO);
	}
	
	@CrossOrigin({"localhost:8080", "http://www.preservacaoquelonios.com.br"})
	@Operation(summary="Listar o Usuário por id")
	@GetMapping(value="/{matricula}",produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UsuarioVO> findById(@PathVariable("matricula") String matricula) {
		UsuarioVO usuarioVO = usuarioService.findById(matricula);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(matricula)).withSelfRel());
		return ResponseEntity.ok().body(usuarioVO);				
	}
	
		
	@Operation(summary="Inserir dados de Usuário")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, 
		         produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UsuarioVO> insert(@Valid @RequestBody UsuarioVO usuarioVO) {		
		UsuarioVO usuarioInseridoVO = usuarioService.insert(usuarioVO);
		usuarioInseridoVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioInseridoVO.getMatricula())).withSelfRel());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(usuarioInseridoVO.getMatricula()).toUri();
		return ResponseEntity.created(uri).body(usuarioInseridoVO);
	}
	
	@Operation(summary="Deletar dados de  Usuário por id")
	@DeleteMapping(value = "/{matricula}")
	public ResponseEntity<Void> delete(@PathVariable ("matricula") String matricula) {
		usuarioService.delete(matricula);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary="Atualizar dados de Usuário por id")
	@PutMapping(value = "/{matricula}",
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UsuarioVO> update(@Valid @PathVariable ("matricula") String matricula, @RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = usuarioService.update(matricula, usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getMatricula())).withSelfRel());
		return ResponseEntity.ok().body(usuarioVO);
	}

	@CrossOrigin({ "localhost:8080", "http://www.preservacaoquelonios.com.br" })
	@Operation(summary = "Listar o Usuário por id")
	@GetMapping(value = "/report-itext", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ModelAndView report() {
		Map<String, Object> model = new HashMap<>();
		List<UsuarioVO> usuarios = usuarioService.findAll();
		model.put("usuarios", usuarios);

		return new ModelAndView(new MyPdfView(), model);
	}
	
	@CrossOrigin({ "localhost:8080", "http://www.preservacaoquelonios.com.br" })
	@Operation(summary = "Listar o Usuário por id")
	@GetMapping(value = "/report-jasper", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE })
	public ResponseEntity<byte[]> getEmployeeRecordReport() {

		try {
			List<UsuarioVO> usuarioList = usuarioService.findAll();

			Map<String, Object> usuarioParams = new HashMap<String, Object>();
			usuarioParams.put("titulo", "Usuários");
			usuarioParams.put("usuarios", new JRBeanCollectionDataSource(usuarioList));
			
			JasperPrint empReport =
					JasperFillManager.fillReport
				   (
						   JasperCompileManager.compileReport(
									ResourceUtils.getFile("classpath:templates/relatorio-usuario.jrxml")
											.getAbsolutePath()) // path of the jasper report
							, usuarioParams // dynamic parameters
							, new JREmptyDataSource()
					);

			HttpHeaders headers = new HttpHeaders();
			//set the PDF format
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("filename", "usuarios.pdf");
			//create the report in PDF format
			return new ResponseEntity<byte[]>
					(JasperExportManager.exportReportToPdf(empReport), headers, HttpStatus.OK);

		} catch(Exception e) {
			return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

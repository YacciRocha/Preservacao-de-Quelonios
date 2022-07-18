package br.com.serasa.pi.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import br.com.serasa.pi.domain.entity.ColetaEntity;
import br.com.serasa.pi.domain.entity.UsuarioEntity;
import br.com.serasa.pi.domain.entity.ViagemEntity;
import br.com.serasa.pi.repository.ColetaRepository;
import br.com.serasa.pi.repository.EclosaoRepository;
import br.com.serasa.pi.repository.SolturaRepository;
import net.sf.jasperreports.engine.JRException;

@RunWith(MockitoJUnitRunner.class)

public class RelatoriosServiceTest {

	@InjectMocks
	private RelatoriosService relatoriosService = new RelatoriosService();

	@Mock
	private ColetaRepository coletaRepository;
	
	@Mock
	private EclosaoRepository eclosaoRepository;
	
	@Mock
	private SolturaRepository solturaRepository;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void WhenTestExportarRelatorioColetaThenReturnSucess() throws JRException, IOException {
		List<ColetaEntity> listaColeta = new ArrayList<>();
		ColetaEntity coleta01 = new ColetaEntity();
		coleta01.setIdColeta(1);
		coleta01.setNumeroCova(123);
		coleta01.setNomePraiaTabuleiro("Nome Praia");
		coleta01.setDataColeta(LocalDate.now());
		coleta01.setQuantidadeOvos(10);
		coleta01.setEspecie("Especie");
		coleta01.setDistanciaAgua(20.0);
		coleta01.setProfundidadePrimeiroOvo(30f);
		coleta01.setProfundidadeTotal(60f);
		coleta01.setLarguraNinho(90f);
		coleta01.setLarguraPata(10f);
		ViagemEntity viagemEntity = new ViagemEntity();
		viagemEntity.setIdViagem(100);
		coleta01.setViagem(viagemEntity);
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setMatricula("123456");
		coleta01.setVoluntario(usuarioEntity);
		listaColeta.add(coleta01);
		Mockito.when(coletaRepository.findAll()).thenReturn(listaColeta);		
		
		ResponseEntity<byte[]> retornado = relatoriosService.exportarRelatorioColeta();
		
		HttpHeaders headersEsperado = new HttpHeaders();
		headersEsperado.setContentType(MediaType.APPLICATION_PDF);
		headersEsperado.setContentDispositionFormData("filename", "coleta.pdf");
		
		assertNotNull(retornado.getBody());
		Mockito.verify(coletaRepository, Mockito.times(1)).findAll();
		assertEquals(HttpStatus.OK, retornado.getStatusCode());
		assertEquals(headersEsperado, retornado.getHeaders());		
	}
}

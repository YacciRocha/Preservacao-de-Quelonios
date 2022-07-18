

package br.com.serasa.pi.test.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serasa.pi.common.ColetaVO;
import br.com.serasa.pi.controller.ColetaController;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.service.ColetaService;

@RunWith(MockitoJUnitRunner.class)
public class ColetaControllerTest {

	@InjectMocks
	private ColetaController coletaController;
	
	@Mock
	private ColetaService coletaService;
	
	@BeforeEach
	public void before() {
		
		MockitoAnnotations.openMocks(this);	
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}
	
	@Test
	void whenFindAllThenReturnSucess() {
		
		
		List<ColetaVO> listaColetaVO = new ArrayList<>();
		ColetaVO coleta01VO = new ColetaVO();
		coleta01VO.setIdColeta(1);
		listaColetaVO.add(coleta01VO);
		ColetaVO coleta02VO = new ColetaVO();
		coleta02VO.setIdColeta(2);
		listaColetaVO.add(coleta02VO);
		
		Page<ColetaVO> pageColetaVOMocado = new PageImpl<>(listaColetaVO);
		
		Mockito.when(coletaService.findAll(Mockito.any())).thenReturn(pageColetaVOMocado);
		
		ResponseEntity<CollectionModel<ColetaVO>> esperado = ResponseEntity.ok(CollectionModel.of(pageColetaVOMocado));
	
		ResponseEntity<CollectionModel<ColetaVO>> retornado = coletaController.findAll(0, 2, "asc");
		
		assertEquals(esperado, retornado);
	}

	@Test
	void whenFindByIdThenReturnSucess() {
		
		ColetaVO coletaVOMocado = new ColetaVO();
		coletaVOMocado.setIdColeta(1);
		
		Mockito.when(coletaService.findById(coletaVOMocado.getIdColeta())).thenReturn(coletaVOMocado);
		
		ResponseEntity<ColetaVO> esperado = ResponseEntity.ok(coletaVOMocado);
		ResponseEntity<ColetaVO> retornado = coletaController.findById(1);
		
		assertEquals(esperado, retornado);
	}

	@Test
	void whenInsertThenReturnSucess() {
		
		ColetaVO coletaVOMocado = new ColetaVO();
		coletaVOMocado.setIdColeta(1);
		
		Mockito.when(coletaService.insert(coletaVOMocado)).thenReturn(coletaVOMocado);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(coletaVOMocado.getIdColeta()).toUri();
		
		ResponseEntity<ColetaVO> esperado = ResponseEntity.created(uri).body(coletaVOMocado);
		ResponseEntity<ColetaVO> retornado = coletaController.insert(coletaVOMocado);
		assertEquals(esperado, retornado);
	}

	@Test
	void whenDeleteThenReturnSucess() {
		
		ResponseEntity<Void> esperado = ResponseEntity.noContent().build();
		ResponseEntity<Void> retornado = coletaController.delete(1);
		
		Mockito.verify(coletaService, Mockito.times(1)).delete(1);
		assertEquals(esperado, retornado);
	}
	
	@Test
	void whenDeleteThenThrowResourceNotFoundException() {
		
		Mockito.doThrow(ResourceNotFoundException.class)
		.when(coletaService).delete(1);

		Assert.assertThrows(ResourceNotFoundException.class, () -> {
			coletaController.delete(1);		
		});		
	} 

	@Test
	void whenUpdateThenReturnSucess() {
	
		ColetaVO coletaVOMocado = new ColetaVO();
		coletaVOMocado.setIdColeta(1);

		Mockito.when(coletaService.update(1, coletaVOMocado)).thenReturn(coletaVOMocado);
		
		ResponseEntity<ColetaVO> esperado = ResponseEntity.ok().body(coletaVOMocado);
		ResponseEntity<ColetaVO> retornado = coletaController.update(1, coletaVOMocado);
		
		assertEquals(esperado, retornado);
	}

	@Test
	void whenFindColetaByNameThenReturnSucess() {
		
		List<ColetaVO> listaColetaVO = new ArrayList<>();
		ColetaVO coleta01VO = new ColetaVO();
		coleta01VO.setIdColeta(1);
		coleta01VO.setNomePraiaTabuleiro("Teste");
		listaColetaVO.add(coleta01VO);
		
		Page<ColetaVO> pageColetaVOMocado = new PageImpl<>(listaColetaVO);
		
		Mockito.when(coletaService.findByName(Mockito.any(), Mockito.any())).thenReturn(pageColetaVOMocado);
		
		ResponseEntity<CollectionModel<ColetaVO>> esperado = ResponseEntity.ok(CollectionModel.of(pageColetaVOMocado));
		ResponseEntity<CollectionModel<ColetaVO>> retornado = coletaController.findColetaByName("Teste", 0, 1, "asc");
		assertEquals(esperado, retornado);
	}
}






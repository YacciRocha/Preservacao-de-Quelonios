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

import br.com.serasa.pi.common.UsuarioVO;
import br.com.serasa.pi.controller.UsuarioController;
import br.com.serasa.pi.exceptions.ResourceNotFoundException;
import br.com.serasa.pi.service.UsuarioService;

@RunWith(MockitoJUnitRunner.class)
class UsuarioControllerTest {
	
	@InjectMocks
	private UsuarioController usuarioController;
	
	@Mock
	private UsuarioService usuarioService;
	
	@BeforeEach
	public void before() {		
		MockitoAnnotations.openMocks(this);		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}
	

	@Test
	void whenTestFindAllThenReturnSucess() {
		List<UsuarioVO> listaUsuarioVO = new ArrayList<>();
		UsuarioVO usuario01VO = new UsuarioVO();
		usuario01VO.setMatricula("123");
		listaUsuarioVO.add(usuario01VO);
		UsuarioVO usuario02VO = new UsuarioVO();
		usuario02VO.setMatricula("456");
		listaUsuarioVO.add(usuario02VO);
		
		Page<UsuarioVO> pageUsuarioVOMocado = new PageImpl<>(listaUsuarioVO);
		Mockito.when(usuarioService.findAll(Mockito.any())).thenReturn(pageUsuarioVOMocado);
		
		ResponseEntity<CollectionModel<UsuarioVO>> esperado = ResponseEntity.ok(CollectionModel.of(pageUsuarioVOMocado));
		ResponseEntity<CollectionModel<UsuarioVO>> retornado = usuarioController.findAll(0, 2, "asc");
		assertEquals(esperado, retornado);
	}

	@Test
	void whenFindByIdThenReturnSucess() {
		
		UsuarioVO usuarioVOMocado = new UsuarioVO();
		usuarioVOMocado.setMatricula("123");
		
		Mockito.when(usuarioService.findById(usuarioVOMocado.getMatricula())).thenReturn(usuarioVOMocado);
		ResponseEntity<UsuarioVO> esperado = ResponseEntity.ok(usuarioVOMocado);
		ResponseEntity<UsuarioVO> retornado = usuarioController.findById("123");
		assertEquals(esperado, retornado);
	}

	@Test
	void whenInsertThenReturnSucess() {
		UsuarioVO usuarioVOMocado = new UsuarioVO();
		usuarioVOMocado.setMatricula("123");
		
		Mockito.when(usuarioService.insert(usuarioVOMocado)).thenReturn(usuarioVOMocado);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{matricula}").buildAndExpand(usuarioVOMocado.getMatricula()).toUri();
		
		ResponseEntity<UsuarioVO> esperado = ResponseEntity.created(uri).body(usuarioVOMocado);
		ResponseEntity<UsuarioVO> retornado = usuarioController.insert(usuarioVOMocado);
		assertEquals(esperado, retornado);
	}

	@Test
	void whenDeleteThenReturnSucess() {
		ResponseEntity<Void> esperado = ResponseEntity.noContent().build();
		ResponseEntity<Void> retornado = usuarioController.delete("123");
		
		Mockito.verify(usuarioService, Mockito.times(1)).delete("123");
		assertEquals(esperado, retornado);
	}

	@Test
	void whenDeleteThenThrowResourceNotFoundException() {
		
		Mockito.doThrow(ResourceNotFoundException.class)
		.when(usuarioService).delete("123");
		
		Assert.assertThrows(ResourceNotFoundException.class, () -> {
			usuarioController.delete("123");		
		});
	}

	@Test
	void whenUpdateThenReturnSucess() {
		
		UsuarioVO usuarioVOMocado = new UsuarioVO();
		usuarioVOMocado.setMatricula("123");
		
		Mockito.when(usuarioService.update("123", usuarioVOMocado)).thenReturn(usuarioVOMocado);
		
		ResponseEntity<UsuarioVO> esperado = ResponseEntity.ok().body(usuarioVOMocado);
		ResponseEntity<UsuarioVO> retornado =usuarioController.update("123", usuarioVOMocado);
		
		assertEquals(esperado, retornado);
	}
}

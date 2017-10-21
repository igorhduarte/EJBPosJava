package br.com.rp.repository.service;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Estado;
import br.com.rp.services.EstadoService;


public class EstadoServiceTest extends AbstractTest{

	@EJB
	EstadoService estadoService;
	
	@Test
	public void deveRecuperarEstadosTest(){
		List<Estado> estados = estadoService.getEstados();
		Assert.assertTrue(estados.size() == 3);
	}

	@Test
	public void deveRecuperarEstadoSaoPaulo(){
		 Estado estado = estadoService.getEstadoPorSigla("SP");
		 Assert.assertEquals(estado.getNome(), "São Paulo");
	}
	
	@Test
	public void deveRecuperarEstadoParana(){
		 Estado estado = estadoService.getEstadoPorSigla("PR");
		 Assert.assertNotEquals(estado.getNome(), "São Paulo");
	}
}

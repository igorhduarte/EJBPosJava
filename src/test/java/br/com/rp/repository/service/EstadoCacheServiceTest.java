package br.com.rp.repository.service;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.services.EstadoCacheService;

public class EstadoCacheServiceTest extends AbstractTest{
	@EJB
	private EstadoCacheService estadoCacheService;
	
	@Test
	public void deveRecuperarEstados(){
		estadoCacheService.getEstados();
		
	}

}

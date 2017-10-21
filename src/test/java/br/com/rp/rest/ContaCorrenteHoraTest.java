package br.com.rp.rest;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.services.movimentacao.MovimentacaoService;

public class ContaCorrenteHoraTest extends AbstractTest {
	@EJB
	private MovimentacaoService service;
	
	
	@Test
	public void TestarContaCorrenteHorario() throws Throwable {
		service.sacar();
	}

}

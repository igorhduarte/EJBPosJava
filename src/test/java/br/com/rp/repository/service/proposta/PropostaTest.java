package br.com.rp.repository.service.proposta;

import static org.junit.Assert.*;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.services.proposta.PropostaService;

public class PropostaTest extends AbstractTest {

	@EJB
	private PropostaService service;
	
	@Test
	public void listarPropostas() {
		fail("Not yet implemented");
	}

}

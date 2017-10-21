package br.com.rp.repository.service.proposta;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Objects;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Proposta;
import br.com.rp.services.proposta.PropostaService;

public class PropostaTest extends AbstractTest {

	@EJB
	private PropostaService service;
	
	@Test
	public void salvarPropostas() {

		Conta conta = new Conta();
		conta.setId(1L);

		Proposta proposta = new Proposta();
		proposta.setConta(conta);
		proposta.setLimite(new BigDecimal("10.10"));
		proposta.setSalario(new BigDecimal("1000.00"));
		
		Proposta actual = service.criarProposta(proposta);
		
		assertTrue(Objects.nonNull(actual.getId()));
		
	}

}

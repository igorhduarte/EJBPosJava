package br.com.rp.rest;

import javax.ejb.EJB;

import br.com.rp.AbstractTest;
import br.com.rp.services.movimentacao.MovimentacaoService;

public class ContaCorrenteHoraTest extends AbstractTest {

	@EJB
	private MovimentacaoService service;

}

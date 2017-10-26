package br.com.rp.service;
import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.services.ContadorRequisicaoService;


public class ContadorRequisicaoTest extends AbstractTest {
	@EJB
	ContadorRequisicaoService contadorRequisicaoService;
	
	@Test
	public void setAcessoTest(){
		contadorRequisicaoService.setAcesso();
		int acessos = contadorRequisicaoService.getAcesso();
		Assert.assertEquals(acessos, 1);
	}

}

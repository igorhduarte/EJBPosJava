package br.com.rp.repository.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.MathResult;
import br.com.rp.services.CalculadoraService;

public class CalculadoraServiceTest  extends AbstractTest{

	@EJB
	CalculadoraService calculadoraService;
	
	@Test
	public void getMediaESomaServiceSyncTest() throws InterruptedException, ExecutionException{
		List<Integer> listInteiros = new ArrayList<Integer>();
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		int soma = calculadoraService.calcularSomaEMediaASync(listInteiros).get().getSoma();
		double media = calculadoraService.calcularSomaEMediaASync(listInteiros).get().getMedia();
		Assert.assertEquals(soma,5);
		Assert.assertEquals(media,1.0);
		
	}
	/*
	@Test
	public void getMediaESomaServiceAsyncTest() throws InterruptedException, ExecutionException{
		List<Integer> listInteiros = new ArrayList<Integer>();
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);
		listInteiros.add(1);		
		MathResult math = calculadoraService.calcularSomaEMedia(listInteiros);
		Assert.assertEquals(math.getSoma().doubleValue(),10);
		Assert.assertEquals(math.getMedia(),1.0);
		
	}*/
}

package br.com.rp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.MathResult;

import com.google.common.collect.Lists;

@Stateless
public class CalculadoraService {
	
	@Asynchronous
	@Interceptors({InterceptorClass.class,InterceptorClass2.class})	
	public Future<MathResult> calcularSomaEMediaASync(List<Integer> lista){
		MathResult math = new MathResult();
		Integer soma = lista.stream().mapToInt(x -> x).sum();
		double media = soma / lista.size();
		math.setSoma(soma);
		math.setMedia(media);
		return new AsyncResult<MathResult>(math);				
	}
	
	public MathResult calcularSomaEMedia(List<Integer> lista) throws InterruptedException, ExecutionException{
	    List<MathResult> listResult = new ArrayList<MathResult>();
			    
		if(lista.size()< 10){
			return calcularSomaEMediaASync(lista).get();
		}
				
		List<List<Integer>> listas = Lists.partition(lista, 2);
		
		Future<MathResult> result = calcularSomaEMediaASync(listas.get(0));
		Future<MathResult> result2 = calcularSomaEMediaASync(listas.get(1));
										
		listResult.add((result.get()));
		listResult.add((result2.get()));
		
		MathResult math =  new MathResult();
		Integer soma = 0;
		for(MathResult mathResult : listResult){
			soma += mathResult.getSoma();
		}
		
		//Integer soma= listResult.stream().forEachOrdered().mapToInt(x -> x.getSoma()).sum(); 
		math.setSoma(soma);
		math.setMedia(soma/lista.size());
		return math;
	}
	
}

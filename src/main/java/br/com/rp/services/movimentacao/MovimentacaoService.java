package br.com.rp.services.movimentacao;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.services.ContaCorrenteDesempenhoInterceptor;

@Stateless
@Interceptors ({ContaCorrenteDesempenhoInterceptor.class})
public class MovimentacaoService {
	
	public void sacar(){
		
	}

}

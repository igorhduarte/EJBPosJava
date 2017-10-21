package br.com.rp.services;

import java.util.Calendar;

import javax.interceptor.InvocationContext;

public class ContaCorrenteInterceptor {
	
	public Object LogTempo(InvocationContext ic) throws Exception {
		Calendar c = Calendar.getInstance();
		int horario = c.get(Calendar.HOUR_OF_DAY);
		if (horario >= 6 && horario < 21 + 0.50) {
			return ic.proceed();
		} else
			throw new RuntimeException("Horario invalido para movimentação");		 
	}
}

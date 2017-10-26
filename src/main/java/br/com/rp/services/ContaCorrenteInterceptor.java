package br.com.rp.services;

import java.util.Calendar;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class ContaCorrenteInterceptor {
	
	@AroundInvoke
	public Object LogTempo(InvocationContext ic) throws Exception {
		
		Calendar c = Calendar.getInstance();
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int minuto = c.get(Calendar.MINUTE);
		
		if (hora >= 6 && (hora < 21 && minuto == 30)) {
		
			return ic.proceed();
		} else
			throw new RuntimeException("Horario invalido para movimentação");		 
	}
}

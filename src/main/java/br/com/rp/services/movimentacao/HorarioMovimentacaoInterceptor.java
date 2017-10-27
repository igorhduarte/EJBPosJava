package br.com.rp.services.movimentacao;

import java.util.Calendar;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class HorarioMovimentacaoInterceptor {
	
	@AroundInvoke
	public Object permitirHorarioMovimentacao(InvocationContext ic) throws Exception {
		
		Calendar c = Calendar.getInstance();
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int minuto = c.get(Calendar.MINUTE);
		
		if (hora <= 6 && (hora < 21 && minuto == 30)) {
			throw new RuntimeException("Horario invalido para movimentação");		 
		} else {
			
			return ic.proceed();
		}
	}
}

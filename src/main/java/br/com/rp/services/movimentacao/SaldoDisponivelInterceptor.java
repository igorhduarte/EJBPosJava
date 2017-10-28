package br.com.rp.services.movimentacao;

import java.math.BigDecimal;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.rp.domain.Conta;

@Interceptor
public class SaldoDisponivelInterceptor {

	@AroundInvoke
	public Object saldoDisponivel(InvocationContext ic) throws Exception {

		BigDecimal valor = (BigDecimal) ic.getParameters()[0];
		Conta conta = (Conta) ic.getParameters()[1];
		
		if (valor.compareTo(conta.getSaldo()) == 1) {
			throw new RuntimeException("Não há saldo suficiente.");
		}

		return ic.proceed();
	}
	
}

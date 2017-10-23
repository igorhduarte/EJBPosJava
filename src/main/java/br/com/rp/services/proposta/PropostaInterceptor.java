package br.com.rp.services.proposta;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.rp.domain.TipoUsuario;

@Interceptor
public class PropostaInterceptor {
	
	@AroundInvoke
	public Object permiteListarPropostas(InvocationContext ic) throws Exception {
		
		if (ic.getParameters()[0] == TipoUsuario.GERENTE_CONTAS) {
			
			return ic.proceed();	
		}
		
		throw new RuntimeException("Usuário não tem permissão para esta operação.");
	}

}

package br.com.rp.services.movimentacao;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.rp.domain.TipoUsuario;
import br.com.rp.domain.Usuario;

@Interceptor
public class PermissaoClienteInterceptor {

	@AroundInvoke
	public Object permicaoIntegracao(InvocationContext ic) throws Exception {

		Usuario usuarioLogado = (Usuario) ic.getParameters()[0];
		
		if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.CLIENTE)) {
			ic.proceed();
		}
		
		throw new RuntimeException("Usuário não tem permissão para esta operaçao");
	}
	
}

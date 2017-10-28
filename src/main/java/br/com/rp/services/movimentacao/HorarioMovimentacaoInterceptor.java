package br.com.rp.services.movimentacao;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Interceptor
public class HorarioMovimentacaoInterceptor {
	
	@EJB
	ConfiguracaoRepository configuracaoRepository;
	
	@AroundInvoke
	public Object permitirHorarioMovimentacao(InvocationContext ic) throws Exception {
		
		Calendar c = Calendar.getInstance();
		int hora = c.get(Calendar.HOUR_OF_DAY);
		int minuto = c.get(Calendar.MINUTE);
		Configuracao configuracao = configuracaoRepository.findById(1L);
		
		if (configuracao != null) {
			
			System.out.println(" HORARIO PERMITIDO MINIMO >>>>>>>>>> " + configuracao.getHorarioMinimoMovimentacao().getTime());
			System.out.println(" HORARIO PERMITIDO MAXIMO >>>>>>>>>> " + configuracao.getHorarioMaximoMovimentacao().getTime());
			
			if ((hora >= configuracao.getHorarioMinimoMovimentacao().get(Calendar.HOUR_OF_DAY)
						&& minuto >= configuracao.getHorarioMinimoMovimentacao().get(Calendar.MINUTE))
					&& (hora <= configuracao.getHorarioMaximoMovimentacao().get(Calendar.HOUR_OF_DAY) 
						&& minuto <= configuracao.getHorarioMaximoMovimentacao().get(Calendar.MINUTE))) {
				
				return ic.proceed();
			} else {
				
				throw new RuntimeException("Horario invalido para movimentação");		 
			}
			
		} 
		
		throw new RuntimeException("Configuração nao criada");
	}
}

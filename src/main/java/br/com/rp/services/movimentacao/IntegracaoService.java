package br.com.rp.services.movimentacao;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.interceptor.Interceptors;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;

import org.jboss.arquillian.core.api.annotation.Inject;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.Usuario;
import br.com.rp.repository.impl.LogInterceptor;

public class IntegracaoService {

	@Inject
	@JMSConnectionFactory("jms/QueueConnectionFactory")
	private JMSContext context;

	@Resource(name = "jms/MovimentacoesQueue")
	private Destination movimentacoesQueue;

	@EJB
	private MovimentacaoService movimentacaoService;

	@EJB
	private ContaService contaService;

	@Interceptors({LogInterceptor.class, IntegracaoInterceptor.class})
	public void enviarMovimentacoesBancoCentralManual(Usuario usuarioLogado) {

		for (Movimentacao mov : movimentacaoService.getAll()) {
			enviarMensagemDeIntegracao(mov.toString());
		}
	}
	
	@Interceptors({LogInterceptor.class})
	@Schedule(minute="0/5")
	public void enviarMovimentacoesBancoCentral() {

		for (Movimentacao mov : movimentacaoService.getAll()) {
			enviarMensagemDeIntegracao(mov.toString());
		}
	}
	

	@Interceptors({LogInterceptor.class, IntegracaoInterceptor.class})
	public void enviarContasSedeManual(Usuario usuarioLogado) {
		for (Conta conta : contaService.getAll()) {
			enviarMensagemDeIntegracao(conta.toString());
		}
	}
	
	@Interceptors({LogInterceptor.class})
	@Schedule(hour="22,0")
	public void enviarContasSede() {
		for (Conta conta : contaService.getAll()) {
			enviarMensagemDeIntegracao(conta.toString());
		}
	}

	private void enviarMensagemDeIntegracao(String msg) {

		context.createProducer().send(movimentacoesQueue, msg);
	}

}

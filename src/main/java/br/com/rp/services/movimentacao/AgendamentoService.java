package br.com.rp.services.movimentacao;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class AgendamentoService extends AbstractRepositoryImpl<Agendamento> {

	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	
	public AgendamentoService() {
		super(Agendamento.class);
	}
	
	@Interceptors({LogInterceptor.class})
	public Agendamento criarAgendamento(Agendamento agendamento) {
		return agendamentoRepository.save(agendamento);
	}
	
	@Interceptors({LogInterceptor.class})
	public void cancelarAgendamento(Agendamento agendamento) {
		agendamento.setCancelado(true);
		agendamento.setDataCancelamento(new Date());
		
		agendamentoRepository.save(agendamento);
	}
	
}

package br.com.rp.services.movimentacao;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;

@Stateless
public class AgendamentoService extends AbstractRepositoryImpl<Agendamento> {

	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	
	public AgendamentoService() {
		super(Agendamento.class);
	}
	
	public Agendamento criarAgendamento(Agendamento agendamento) {
		return agendamentoRepository.save(agendamento);
	}
	
	public void cancelarAgendamento(Agendamento agendamento) {
		agendamento.setCancelado(true);
		agendamento.setDataCancelamento(new Date());
		
		agendamentoRepository.save(agendamento);
	}
	
}

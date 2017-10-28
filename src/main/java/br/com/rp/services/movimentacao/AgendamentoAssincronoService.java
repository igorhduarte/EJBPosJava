package br.com.rp.services.movimentacao;

import java.util.Date;
import java.util.List;

import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.repository.AgendamentoRepository;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;

@Stateless
public class AgendamentoAssincronoService extends AbstractRepositoryImpl<Agendamento> {

	@EJB
	private AgendamentoRepository agendamentoRepository;
	
	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	
	@EJB
	private ContaRepository contaRepository;
	
	public AgendamentoAssincronoService() {
		super(Agendamento.class);
	}
	
	@Schedule(hour="18", minute="30")
	@Asynchronous
	public void executarMovimentacoesAgendadas() {
		List<Agendamento> agendamentos = agendamentoRepository.getAll();
		
		agendamentos.stream().filter(a -> a.getData().equals(new Date())).map(Agendamento::getMovimentacao).map(mov -> {
			
			if (mov.getTipoMovimentacao().equals(TipoMovimentacao.DEBITO) 
					|| mov.getTipoMovimentacao().equals(TipoMovimentacao.TRANSFERENCIA_DEBITO)) {

				mov.getContaOrigem().setSaldo(mov.getContaOrigem().getSaldo().subtract(mov.getValor()));
				contaRepository.save(mov.getContaOrigem());

				mov.getContaDestino().setSaldo(mov.getContaDestino().getSaldo().add(mov.getValor()));
				contaRepository.save(mov.getContaDestino());
			}
			
			if (mov.getTipoMovimentacao().equals(TipoMovimentacao.DEPOSITO) 
					|| mov.getTipoMovimentacao().equals(TipoMovimentacao.TRANSFERENCIA_DEPOSITO)
					|| mov.getTipoMovimentacao().equals(TipoMovimentacao.DEPOSITO_CHEQUE)) {

				mov.getContaDestino().setSaldo(mov.getContaDestino().getSaldo().add(mov.getValor()));
				contaRepository.save(mov.getContaDestino());
			}
			
			return mov;
		});
	}
}

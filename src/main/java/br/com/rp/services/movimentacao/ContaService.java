package br.com.rp.services.movimentacao;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;

@Stateless
public class ContaService extends AbstractRepositoryImpl<Conta> {
	
	@EJB
	private ContaRepository contaRepository;
	
	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	
	public ContaService() {
		super(Conta.class);
	}
	
	public Conta consultarSaldo(Conta conta) {
		return contaRepository.consultarSaldo(conta);
	}

	@Interceptors({HorarioMovimentacaoInterceptor.class, SaldoDisponivelInterceptor.class})
	public BigDecimal debitar(BigDecimal valor, Conta contaOrigem, Conta contaDestino) {
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		movimentacao.setContaOrigem(contaOrigem);
		movimentacao.setContaDestino(contaDestino);
		movimentacao.setData(new Date());
		movimentacao.setValor(valor);

		movimentacaoRepository.save(movimentacao);
		
		BigDecimal saldoAtual = contaOrigem.getSaldo().subtract(valor);
		contaOrigem.setSaldo(saldoAtual);
		contaRepository.save(contaOrigem);
		
		contaDestino.getSaldo().add(valor);
		contaDestino.setSaldo(saldoAtual);
		contaRepository.save(contaDestino);
		
		return saldoAtual;
	}

	@Interceptors(HorarioMovimentacaoInterceptor.class)
	public BigDecimal depositar(BigDecimal valor, Conta conta) {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.DEPOSITO);
		movimentacao.setContaDestino(conta);
		movimentacao.setData(new Date());
		movimentacao.setValor(valor);

		movimentacaoRepository.save(movimentacao);
		
		BigDecimal saldoAtual = conta.getSaldo().add(valor);
		conta.setSaldo(saldoAtual);
		contaRepository.save(conta);
		
		return saldoAtual;
	}
	
	@Interceptors(SaldoDisponivelInterceptor.class)
	public BigDecimal transferir(BigDecimal valor, Conta contaOrigem, Conta contaDestino) {

		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.TRANSFERENCIA_DEBITO);
		movimentacao.setContaOrigem(contaOrigem);
		movimentacao.setContaDestino(contaDestino);
		movimentacao.setData(new Date());
		movimentacao.setValor(valor);

		movimentacaoRepository.save(movimentacao);

		contaDestino.getSaldo().add(valor);
		BigDecimal saldoAtual = contaOrigem.getSaldo().subtract(valor);
		contaOrigem.setSaldo(saldoAtual);
		
		contaRepository.save(contaOrigem);
		contaRepository.save(contaDestino);
		
		return saldoAtual;
	}

}

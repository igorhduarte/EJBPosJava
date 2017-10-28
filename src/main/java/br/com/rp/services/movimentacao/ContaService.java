package br.com.rp.services.movimentacao;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Agendamento;
import br.com.rp.domain.Cheque;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.domain.Usuario;
import br.com.rp.repository.ContaRepository;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class ContaService extends AbstractRepositoryImpl<Conta> {
	
	@EJB
	private ContaRepository contaRepository;
	
	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	
	@EJB
	private AgendamentoService agendamentoService;
	
	public ContaService() {
		super(Conta.class);
	}
	
	public Conta consultarSaldo(Conta conta) {
		return contaRepository.consultarSaldo(conta);
	}

	@Interceptors({LogInterceptor.class, HorarioMovimentacaoInterceptor.class})
	public BigDecimal debitar(Usuario usuarioLogado, BigDecimal valor, Conta contaOrigem, String docPagamento) {
		
		validarSaldo(valor, contaOrigem);
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.DEBITO);
		movimentacao.setContaOrigem(contaOrigem);
		movimentacao.setData(new Date());
		movimentacao.setValor(valor);
		movimentacao.setDocPagamento(docPagamento);

		movimentacaoRepository.save(movimentacao);
		
		BigDecimal saldoAtual = contaOrigem.getSaldo().subtract(valor);
		contaOrigem.setSaldo(saldoAtual);
		contaRepository.save(contaOrigem);
		
		return saldoAtual;
	}

	@Interceptors({LogInterceptor.class, HorarioMovimentacaoInterceptor.class})
	public BigDecimal depositar(Usuario usuarioLogado, BigDecimal valor, Conta conta) {

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
	
	@Interceptors({LogInterceptor.class})
	public void depositarCheque(Usuario usuarioLogado, Conta conta,Cheque cheque) {

		Movimentacao movimentacao = new Movimentacao();
		Agendamento agendamento = new Agendamento();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.DEPOSITO_CHEQUE);
		movimentacao.setContaDestino(conta);
		movimentacao.setData(new Date());
		movimentacao.setCheque(cheque);
		movimentacao.setValor(cheque.getValor());
		
		Movimentacao save = movimentacaoRepository.save(movimentacao);
		
		agendamento.setData(cheque.getDataDeposito());
		agendamento.setMovimentacao(save);
		agendamentoService.criarAgendamento(usuarioLogado,agendamento);
		
		BigDecimal saldoAtual = conta.getSaldo().add(cheque.getValor());
		conta.setSaldo(saldoAtual);
		contaRepository.save(conta);
		
	}
	
	@Interceptors({LogInterceptor.class})
	public BigDecimal transferirInterno(Usuario usuarioLogado, BigDecimal valor, Conta contaOrigem, Conta contaDestino) {
		
		validarSaldo(valor, contaOrigem);

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

	private void validarSaldo(BigDecimal valor, Conta contaOrigem) {
		if (valor.compareTo(contaOrigem.getSaldo()) == 1) {
			throw new RuntimeException("Não há saldo suficiente.");
		}
	}
	
	@Interceptors({LogInterceptor.class})
	public BigDecimal transferirExterno(Usuario usuarioLogado, BigDecimal valor, Conta contaOrigem, Conta contaDestino, String codBanco, String cpfCnpjBeneficiario) {

		validarSaldo(valor, contaOrigem);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setTipoMovimentacao(TipoMovimentacao.TRANSFERENCIA_DEBITO);
		movimentacao.setContaOrigem(contaOrigem);
		movimentacao.setContaDestino(contaDestino);
		movimentacao.setCodBanco(codBanco);
		movimentacao.setCpfCnpjBeneficiario(cpfCnpjBeneficiario);
		movimentacao.setData(new Date());
		movimentacao.setValor(valor);

		movimentacaoRepository.save(movimentacao);

		BigDecimal saldoAtual = contaOrigem.getSaldo().subtract(valor);
		contaOrigem.setSaldo(saldoAtual);
		
		contaRepository.save(contaOrigem);
		
		return saldoAtual;
	}

}

package br.com.rp.services.conta;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.ContaCorrente;
import br.com.rp.repository.ContaCorrenteRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;

@Stateless
public class ContaCorrenteService extends AbstractRepositoryImpl<ContaCorrente>{
	
	@EJB
	private ContaCorrenteRepository contaCorrenteRepository;
	
	public ContaCorrenteService() {
		super(ContaCorrente.class);
	}
	
	public ContaCorrente consultarSaldo(ContaCorrente contaCorrente) {
		return contaCorrenteRepository.consultarSaldo(contaCorrente);
	}
	
	public BigDecimal debitar(BigDecimal valor, ContaCorrente contaCorrente) {
		
		if (valor.compareTo(contaCorrente.getSaldo()) == 1) {
			throw new RuntimeException("Não há saldo suficiente.");
		}
		
		BigDecimal saldoAtual = contaCorrente.getSaldo().subtract(valor);
		contaCorrente.setSaldo(saldoAtual);
		contaCorrenteRepository.save(contaCorrente);
		
		return saldoAtual;
	}
	
	public BigDecimal depositar(BigDecimal valor, ContaCorrente contaCorrente) {
		
		BigDecimal saldoAtual = contaCorrente.getSaldo().add(valor);
		contaCorrente.setSaldo(saldoAtual);
		contaCorrenteRepository.save(contaCorrente);
		
		return saldoAtual;
	}
  
}

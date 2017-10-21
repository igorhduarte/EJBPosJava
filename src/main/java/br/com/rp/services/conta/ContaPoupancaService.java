package br.com.rp.services.conta;

import br.com.rp.domain.Conta;
import br.com.rp.repository.impl.AbstractRepositoryImpl;

public class ContaPoupancaService extends AbstractRepositoryImpl<Conta>{

	public ContaPoupancaService() {
		super(Conta.class);
		
	}
	
	public Double consultarSaldo(Conta conta, Conta agencia) {
		super.getAll();
		return(consultarSaldoBanco(conta.getNumero(), conta.getAgencia()));
	}  
}
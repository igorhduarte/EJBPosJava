package br.com.rp.services.movimentacao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.repository.MovimentacaoRepository;
import br.com.rp.repository.impl.AbstractRepositoryImpl;

@Stateless
public class MovimentacaoService extends AbstractRepositoryImpl<Movimentacao> {

	@EJB
	private MovimentacaoRepository movimentacaoRepository;
	
	public MovimentacaoService() {
		super(Movimentacao.class);
	}
	
	public List<Movimentacao> somenteDebitos(Conta conta) {
		return movimentacaoRepository.somenteByTipoMovimentacao(conta, TipoMovimentacao.DEBITO);
	}
	
	public List<Movimentacao> somenteDepositos(Conta conta) {
		return movimentacaoRepository.somenteByTipoMovimentacao(conta, TipoMovimentacao.DEPOSITO);
	}
	
	public List<Movimentacao> somenteSaques(Conta conta) {
		return movimentacaoRepository.somenteByTipoMovimentacao(conta, TipoMovimentacao.SAQUE);
	}

	

}

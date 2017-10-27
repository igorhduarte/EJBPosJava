package br.com.rp.repository;

import java.util.List;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;

public interface MovimentacaoRepository extends Repository<Movimentacao> {

	List<Movimentacao> somenteByTipoMovimentacao(Conta conta, TipoMovimentacao tipoMovimentacao);
	
}

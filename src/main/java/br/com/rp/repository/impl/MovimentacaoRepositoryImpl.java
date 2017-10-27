package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Conta;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.TipoMovimentacao;
import br.com.rp.repository.MovimentacaoRepository;

@Stateless
public class MovimentacaoRepositoryImpl extends AbstractRepositoryImpl<Movimentacao> implements MovimentacaoRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;

	public MovimentacaoRepositoryImpl() {
		super(Movimentacao.class);
	}

	@Override
	public List<Movimentacao> somenteByTipoMovimentacao(Conta conta, TipoMovimentacao tipoMovimentacao) {

		return em.createQuery(
				String.format(
						"from Movimentacao m where m.tipoMovimentacao = '%s' and m.conta.id = %d",
						tipoMovimentacao.name(),
						conta.getId()
						)
				, Movimentacao.class).getResultList();
	}

}

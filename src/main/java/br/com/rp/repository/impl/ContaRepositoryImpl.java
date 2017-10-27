package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Conta;
import br.com.rp.repository.ContaRepository;

@Stateless
public class ContaRepositoryImpl extends AbstractRepositoryImpl<Conta> implements ContaRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public ContaRepositoryImpl() {
		super(Conta.class);
	}

	public Conta consultarSaldo(Conta conta) {
		
		return (Conta) em.createQuery(
		String.format(
			"from Conta c where c.agencia = '%s' and c.numero = '%s'", 
			conta.getAgencia(), 
			conta.getNumero())
		).getSingleResult();
	}
	
}

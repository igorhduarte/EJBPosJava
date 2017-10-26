package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.ContaCorrente;
import br.com.rp.repository.ContaCorrenteRepository;

@Stateless
public class ContaCorrenteRepositoryImpl extends AbstractRepositoryImpl<ContaCorrente> implements ContaCorrenteRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public ContaCorrenteRepositoryImpl() {
		super(ContaCorrente.class);
	}

	public ContaCorrente consultarSaldo(ContaCorrente contaCorrente) {
		
		return (ContaCorrente) em.createQuery(
		String.format(
			"from ContaCorrente cc where cc.agencia = '%s' and cc.numero = '%s'", 
			contaCorrente.getAgencia(), 
			contaCorrente.getNumero())
		).getSingleResult();
	}
	
}

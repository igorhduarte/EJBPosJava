package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Cidade;
import br.com.rp.repository.CidadeRepository;

@Stateless
public class CidadeRepositoryImpl extends AbstractRepositoryImpl<Cidade> implements CidadeRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public CidadeRepositoryImpl() {
		super(Cidade.class);
	}
	
}

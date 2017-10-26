package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Pessoa;
import br.com.rp.repository.PessoaRepository;

@Stateless
public class PessoaRepositoryImpl extends AbstractRepositoryImpl<Pessoa> implements PessoaRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public PessoaRepositoryImpl() {
		super(Pessoa.class);
	}
	
}

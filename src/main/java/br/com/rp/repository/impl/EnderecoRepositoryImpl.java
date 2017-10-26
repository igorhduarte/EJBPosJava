package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Endereco;
import br.com.rp.repository.EnderecoRepository;

@Stateless
public class EnderecoRepositoryImpl extends AbstractRepositoryImpl<Endereco> implements EnderecoRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public EnderecoRepositoryImpl() {
		super(Endereco.class);
	}
	
}

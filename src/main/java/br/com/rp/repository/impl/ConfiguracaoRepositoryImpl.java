package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Configuracao;
import br.com.rp.repository.ConfiguracaoRepository;

@Stateless
public class ConfiguracaoRepositoryImpl extends AbstractRepositoryImpl<Configuracao> implements ConfiguracaoRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public ConfiguracaoRepositoryImpl() {
		super(Configuracao.class);
	}
	
}

package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Agendamento;
import br.com.rp.repository.AgendamentoRepository;

@Stateless
public class AgendamentoRepositoryImpl extends AbstractRepositoryImpl<Agendamento> implements AgendamentoRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public AgendamentoRepositoryImpl() {
		super(Agendamento.class);
	}
	
}

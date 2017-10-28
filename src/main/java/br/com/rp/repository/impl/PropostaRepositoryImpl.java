package br.com.rp.repository.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Proposta;
import br.com.rp.repository.PropostaRepository;

@Stateless
public class PropostaRepositoryImpl extends AbstractRepositoryImpl<Proposta> implements PropostaRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;
	
	public PropostaRepositoryImpl() {
		super(Proposta.class);
	}

	public List<Proposta> findPropostasAceitas() {
		
		return em.createQuery("from Proposta p where p.aceita == true ", Proposta.class).getResultList();
	}
	
	public List<Proposta> findPropostasRejeitadas() {
		
		return em.createQuery("from Proposta p where p.aceita == false ", Proposta.class).getResultList();
	}
	
public List<Proposta> findPropostasAceitasPorRegiao(String regiao) {
		
		return em.createQuery("from Proposta p "
				+ "left join p.pessoa.cidade.estado.regiao as regiao"
				+ "where p.aceita == true and regiao == "+ regiao, Proposta.class).getResultList();
	}
	
	public List<Proposta> findPropostasRejeitadasPorRegiao(String regiao) {
		
		return em.createQuery("from Proposta p "
				+ "left join p.pessoa.cidade.estado.regiao as regiao"
				+ "where p.aceita == false and regiao == "+ regiao, Proposta.class).getResultList();
	}
	
}

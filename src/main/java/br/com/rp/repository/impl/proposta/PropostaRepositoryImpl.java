package br.com.rp.repository.impl.proposta;

import javax.ejb.Stateless;

import br.com.rp.domain.Proposta;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.repository.proposta.PropostaRepository;

@Stateless
public class PropostaRepositoryImpl extends AbstractRepositoryImpl<Proposta> implements PropostaRepository {

	public PropostaRepositoryImpl() {
		super(Proposta.class);
	}

}

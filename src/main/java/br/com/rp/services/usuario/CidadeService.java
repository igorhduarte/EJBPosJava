package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Cidade;
import br.com.rp.repository.CidadeRepository;

@Stateless
public class CidadeService {

	@EJB
	private CidadeRepository repository;

	public Cidade save(Cidade cidade) {

		return repository.save(cidade);
	}

}

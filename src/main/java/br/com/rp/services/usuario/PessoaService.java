package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Pessoa;
import br.com.rp.repository.PessoaRepository;

@Stateless
public class PessoaService {

	@EJB
	private PessoaRepository repository;

	public Pessoa save(Pessoa pessoa) {

		return repository.save(pessoa);
	}

}

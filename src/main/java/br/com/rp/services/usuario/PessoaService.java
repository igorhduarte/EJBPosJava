package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Pessoa;
import br.com.rp.repository.PessoaRepository;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class PessoaService {

	@EJB
	private PessoaRepository repository;

	@Interceptors(LogInterceptor.class)
	public Pessoa save(Pessoa pessoa) {

		return repository.save(pessoa);
	}

}

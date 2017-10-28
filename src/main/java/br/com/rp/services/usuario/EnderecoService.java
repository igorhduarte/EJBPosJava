package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Endereco;
import br.com.rp.repository.EnderecoRepository;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class EnderecoService {

	@EJB
	private EnderecoRepository repository;

	@Interceptors(LogInterceptor.class)
	public Endereco save(Endereco endereco) {

		return repository.save(endereco);
	}

}

package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Cidade;
import br.com.rp.repository.CidadeRepository;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class CidadeService {

	@EJB
	private CidadeRepository repository;

	@Interceptors(LogInterceptor.class)
	public Cidade save(Cidade cidade) {

		return repository.save(cidade);
	}

}

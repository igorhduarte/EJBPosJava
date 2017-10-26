package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Endereco;
import br.com.rp.repository.EnderecoRepository;

@Stateless
public class EnderecoService {

	@EJB
	private EnderecoRepository repository;

	public Endereco save(Endereco endereco) {

		return repository.save(endereco);
	}

}

package br.com.rp.services.proposta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Proposta;
import br.com.rp.repository.proposta.PropostaRepository;

@Stateless
public class PropostaService {
	
	@EJB
	private PropostaRepository repository;
	
	public List<Proposta> listarPropostas() {
		
		return repository.getAll();	
	}
	
	public Proposta criarProposta(Proposta proposta) {
		
		return repository.save(proposta);	
	}

}

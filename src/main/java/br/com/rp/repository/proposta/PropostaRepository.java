package br.com.rp.repository.proposta;

import java.util.List;

import br.com.rp.domain.Proposta;
import br.com.rp.repository.Repository;

public interface PropostaRepository extends Repository<Proposta> {

	List<Proposta> findPropostasAceitas();

	List<Proposta> findPropostasRejeitadas();
	
}

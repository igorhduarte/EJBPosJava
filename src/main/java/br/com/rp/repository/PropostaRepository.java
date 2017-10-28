package br.com.rp.repository;

import java.util.List;

import br.com.rp.domain.Proposta;

public interface PropostaRepository extends Repository<Proposta> {

	List<Proposta> findPropostasAceitas();

	List<Proposta> findPropostasRejeitadas();
	
	List<Proposta> findPropostasAceitasPorRegiao(String regiao);

	List<Proposta> findPropostasRejeitadasPorRegiao(String regiao);
	
}

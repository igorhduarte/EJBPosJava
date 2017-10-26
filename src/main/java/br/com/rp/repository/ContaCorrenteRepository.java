package br.com.rp.repository;


import br.com.rp.domain.ContaCorrente;

public interface ContaCorrenteRepository extends Repository<ContaCorrente> {

	ContaCorrente consultarSaldo(ContaCorrente contaCorrente);
	
}

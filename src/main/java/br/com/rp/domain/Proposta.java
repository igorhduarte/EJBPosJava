package br.com.rp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Proposta extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Conta conta;

	@NotNull
	private BigDecimal limite;

	@NotNull
	private BigDecimal salario;

	private Boolean aceita;

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Boolean getAceita() {
		return aceita;
	}

	public void setAceita(Boolean aceita) {
		this.aceita = aceita;
	}

}

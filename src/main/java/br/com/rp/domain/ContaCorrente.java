package br.com.rp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
public class ContaCorrente extends Conta {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal saldo;

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

}

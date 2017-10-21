package br.com.rp.domain;

import java.math.BigDecimal;

public class ContaPoupanca extends Conta {

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

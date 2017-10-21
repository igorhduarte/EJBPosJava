package br.com.rp.domain;

import java.math.BigDecimal;

public class CartaoCredito {

	private BigDecimal limite;
	private BigDecimal totalAberto;

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public BigDecimal getTotalAberto() {
		return totalAberto;
	}

	public void setTotalAberto(BigDecimal totalAberto) {
		this.totalAberto = totalAberto;
	}

}

package br.com.rp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;

@Entity
public class CartaoCredito extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

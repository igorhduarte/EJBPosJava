package br.com.rp.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Movimentacao extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date data;
	private String tipoMovimentacao;
	private Conta conta;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}

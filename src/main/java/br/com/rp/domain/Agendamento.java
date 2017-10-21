package br.com.rp.domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Agendamento extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Movimentacao movimentacao;
	private Date data;

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}

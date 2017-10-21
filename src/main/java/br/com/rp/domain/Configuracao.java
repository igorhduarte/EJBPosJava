package br.com.rp.domain;

import java.util.Calendar;

import javax.persistence.Entity;

@Entity
public class Configuracao extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Calendar horarioMinimoMovimentacao;
	private Calendar horarioMaximoMovimentacao;

	public Calendar getHorarioMinimoMovimentacao() {
		return horarioMinimoMovimentacao;
	}

	public void setHorarioMinimoMovimentacao(Calendar horarioMinimoMovimentacao) {
		this.horarioMinimoMovimentacao = horarioMinimoMovimentacao;
	}

	public Calendar getHorarioMaximoMovimentacao() {
		return horarioMaximoMovimentacao;
	}

	public void setHorarioMaximoMovimentacao(Calendar horarioMaximoMovimentacao) {
		this.horarioMaximoMovimentacao = horarioMaximoMovimentacao;
	}

}

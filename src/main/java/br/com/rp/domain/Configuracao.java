package br.com.rp.domain;

import java.util.Calendar;

public class Configuracao {

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

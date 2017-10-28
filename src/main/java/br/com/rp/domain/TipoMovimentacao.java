package br.com.rp.domain;

public enum TipoMovimentacao {

	DEBITO("DEBITO"), DEPOSITO("DEPOSITO"), DEPOSITO_CHEQUE("DEPOSITO_CHEQUE"), SAQUE("SAQUE"), TRANSFERENCIA_DEPOSITO("TRANSFERENCIA_DEPOSITO"), TRANSFERENCIA_DEBITO("TRANSFERENCIA_DEBITO");

	private TipoMovimentacao(String tipo) {
		this.tipo = tipo;
	}

	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}

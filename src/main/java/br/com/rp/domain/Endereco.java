package br.com.rp.domain;

import javax.persistence.Entity;

@Entity
public class Endereco extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String logradouro;
	private String numero;
	private Cidade cidade;

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}

package br.com.rp.domain;

import javax.persistence.Entity;

@Entity
public class Conta extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String numero;
	private String agencia;
	private Cliente cliente;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}

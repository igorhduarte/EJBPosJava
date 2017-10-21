package br.com.rp.domain;

import javax.persistence.Entity;

@Entity
public class Cidade extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private EstadoEnum estado;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public EstadoEnum getEstado() {
		return estado;
	}

	public void setEstado(EstadoEnum estado) {
		this.estado = estado;
	}

}

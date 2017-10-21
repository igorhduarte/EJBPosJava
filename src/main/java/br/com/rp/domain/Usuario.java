package br.com.rp.domain;

import javax.persistence.Entity;

@Entity
public class Usuario extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Pessoa pessoa;
	private String login;
	private String senha;
	private String tipoUsuario;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}

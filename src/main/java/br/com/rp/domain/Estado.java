package br.com.rp.domain;

public enum Estado {

	PARANA("PR", "Paraná", "SUL"), 
	SAO_PAULO("SP", "São Paulo", "SUDESTE"), 
	SANTA_CATARINA("SC", "Santa Catarina", "SUL"), 
	PERNAMBUCO("PE", "Pernambuco", "NORDESTE");

	private Estado(String sigla, String nome, String regiao) {
		this.sigla = sigla;
		this.nome = nome;
		this.regiao = regiao;
	}

	private String sigla;
	private String nome;
	private String regiao;

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

}

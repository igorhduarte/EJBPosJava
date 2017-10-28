package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Movimentacao extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Date data;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	@ManyToOne
	private Conta contaOrigem;

	@ManyToOne
	private Conta contaDestino;
	
	@OneToOne
	private Cheque cheque;

	@NotNull
	private BigDecimal valor;
	
	private String codBanco;
	
	private String cpfCnpjBeneficiario;

	private String docPagamento;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Conta contaDestino) {
		this.contaDestino = contaDestino;
	}

	public String getCodBanco() {
		return codBanco;
	}

	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	public String getCpfCnpjBeneficiario() {
		return cpfCnpjBeneficiario;
	}

	public void setCpfCnpjBeneficiario(String cpfCnpjBeneficiario) {
		this.cpfCnpjBeneficiario = cpfCnpjBeneficiario;
	}

	public Cheque getCheque() {
		return cheque;
	}

	public void setCheque(Cheque cheque) {
		this.cheque = cheque;
	}

	public String getDocPagamento() {
		return docPagamento;
	}

	public void setDocPagamento(String docPagamento) {
		this.docPagamento = docPagamento;
	}
	

}

package br.com.rp.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class Cheque extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String codCMC7;
	
	@NotNull
	private String nroCheque;
	
	@NotNull
	private String cpfCnpj;
	
	@NotNull
	private BigDecimal valor;
	
	@NotNull
	private Date dataDeposito;
	
	private String status;

	public String getCodCMC7() {
		return codCMC7;
	}

	public void setCodCMC7(String codCMC7) {
		this.codCMC7 = codCMC7;
	}

	public String getNroCheque() {
		return nroCheque;
	}

	public void setNroCheque(String nroCheque) {
		this.nroCheque = nroCheque;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataDeposito() {
		return dataDeposito;
	}

	public void setDataDeposito(Date dataDeposito) {
		this.dataDeposito = dataDeposito;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}

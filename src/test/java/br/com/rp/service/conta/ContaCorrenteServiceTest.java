package br.com.rp.service.conta;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cidade;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Endereco;
import br.com.rp.domain.EstadoEnum;
import br.com.rp.domain.Pessoa;
import br.com.rp.services.conta.ContaCorrenteService;
import br.com.rp.services.usuario.CidadeService;
import br.com.rp.services.usuario.EnderecoService;
import br.com.rp.services.usuario.PessoaService;
import br.com.rp.services.usuario.UsuarioService;

public class ContaCorrenteServiceTest extends AbstractTest {
	
	@EJB
	private ContaCorrenteService contaCorrenteService;

	@EJB
	private PessoaService pessoaService;
	
	@EJB
	private EnderecoService enderecoService;
	
	@EJB
	private CidadeService cidadeService;
	
	@EJB
	private UsuarioService usuarioService;

	@Test
	public void consultarSaldo() {

		Cidade cidade = new Cidade();
		cidade.setEstado(EstadoEnum.PARANA);
		cidade.setNome("Maringá");

		Cidade cidadeSaved = cidadeService.save(cidade);
		
		Endereco endereco = new Endereco();
		endereco.setNumero("123");
		endereco.setLogradouro("Rua Tal");
		endereco.setCidade(cidadeSaved);
		
		Endereco enderecoSaved = enderecoService.save(endereco);

		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa");
		pessoa.setCpf("1111111111");
		pessoa.setEmail("pessoa@email.com");
		pessoa.setEndereco(enderecoSaved);
		
		Pessoa clienteSaved = pessoaService.save(pessoa);
		
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("3333-3");
		contaCorrente.setAgencia("333333-3");
		contaCorrente.setSaldo(new BigDecimal("1000.00"));
		contaCorrente.setPessoa(clienteSaved);
		
		ContaCorrente contaCorrenteSaved = contaCorrenteService.save(contaCorrente);

		ContaCorrente contaCorrenteConsultado = contaCorrenteService.consultarSaldo(contaCorrenteSaved);
		
		assertThat(contaCorrenteConsultado.getSaldo(), equalTo(new BigDecimal("1000.00")));
	}
	
	@Test
	public void debitar() {
		
		Cidade cidade = new Cidade();
		cidade.setEstado(EstadoEnum.PARANA);
		cidade.setNome("Maringá");
		
		Cidade cidadeSaved = cidadeService.save(cidade);
		
		Endereco endereco = new Endereco();
		endereco.setNumero("123");
		endereco.setLogradouro("Rua Tal");
		endereco.setCidade(cidadeSaved);
		
		Endereco enderecoSaved = enderecoService.save(endereco);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa");
		pessoa.setCpf("1111111111");
		pessoa.setEmail("pessoa@email.com");
		pessoa.setEndereco(enderecoSaved);
		
		Pessoa clienteSaved = pessoaService.save(pessoa);
		
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("3333-3");
		contaCorrente.setAgencia("333333-3");
		contaCorrente.setSaldo(new BigDecimal("1000.00"));
		contaCorrente.setPessoa(clienteSaved);
		
		ContaCorrente contaCorrenteSaved = contaCorrenteService.save(contaCorrente);
		
		BigDecimal actual = contaCorrenteService.debitar(new BigDecimal("55.99"), contaCorrenteSaved);

		ContaCorrente contaCorrenteConsultado = contaCorrenteService.consultarSaldo(contaCorrenteSaved);
		
		assertThat(actual, equalTo(contaCorrenteConsultado.getSaldo()));
	}
	
	@Test(expected = RuntimeException.class)
	public void debitarSemSaldoSuficiente() {
		
		Cidade cidade = new Cidade();
		cidade.setEstado(EstadoEnum.PARANA);
		cidade.setNome("Maringá");
		
		Cidade cidadeSaved = cidadeService.save(cidade);
		
		Endereco endereco = new Endereco();
		endereco.setNumero("123");
		endereco.setLogradouro("Rua Tal");
		endereco.setCidade(cidadeSaved);
		
		Endereco enderecoSaved = enderecoService.save(endereco);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa");
		pessoa.setCpf("1111111111");
		pessoa.setEmail("pessoa@email.com");
		pessoa.setEndereco(enderecoSaved);
		
		Pessoa clienteSaved = pessoaService.save(pessoa);
		
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("3333-3");
		contaCorrente.setAgencia("333333-3");
		contaCorrente.setSaldo(new BigDecimal("1000.00"));
		contaCorrente.setPessoa(clienteSaved);
		
		ContaCorrente contaCorrenteSaved = contaCorrenteService.save(contaCorrente);
		
		contaCorrenteService.debitar(new BigDecimal("1055.99"), contaCorrenteSaved);
	}
	
	@Test
	public void depositar() {
		
		Cidade cidade = new Cidade();
		cidade.setEstado(EstadoEnum.PARANA);
		cidade.setNome("Maringá");
		
		Cidade cidadeSaved = cidadeService.save(cidade);
		
		Endereco endereco = new Endereco();
		endereco.setNumero("123");
		endereco.setLogradouro("Rua Tal");
		endereco.setCidade(cidadeSaved);
		
		Endereco enderecoSaved = enderecoService.save(endereco);
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Pessoa");
		pessoa.setCpf("1111111111");
		pessoa.setEmail("pessoa@email.com");
		pessoa.setEndereco(enderecoSaved);
		
		Pessoa clienteSaved = pessoaService.save(pessoa);
		
		ContaCorrente contaCorrente = new ContaCorrente();
		contaCorrente.setNumero("3333-3");
		contaCorrente.setAgencia("333333-3");
		contaCorrente.setSaldo(new BigDecimal("1000.00"));
		contaCorrente.setPessoa(clienteSaved);
		
		ContaCorrente contaCorrenteSaved = contaCorrenteService.save(contaCorrente);
		
		BigDecimal actual = contaCorrenteService.debitar(new BigDecimal("55.99"), contaCorrenteSaved);
		
		ContaCorrente contaCorrenteConsultado = contaCorrenteService.consultarSaldo(contaCorrenteSaved);
		
		assertThat(actual, equalTo(contaCorrenteConsultado.getSaldo()));
	}
	
}

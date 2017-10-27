package br.com.rp.service.conta;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cidade;
import br.com.rp.domain.Conta;
import br.com.rp.domain.ContaCorrente;
import br.com.rp.domain.Endereco;
import br.com.rp.domain.Estado;
import br.com.rp.domain.Movimentacao;
import br.com.rp.domain.Pessoa;
import br.com.rp.services.movimentacao.ContaService;
import br.com.rp.services.movimentacao.MovimentacaoService;
import br.com.rp.services.usuario.CidadeService;
import br.com.rp.services.usuario.EnderecoService;
import br.com.rp.services.usuario.PessoaService;
import br.com.rp.services.usuario.UsuarioService;

public class ContaCorrenteServiceTest extends AbstractTest {
	
	@EJB
	private ContaService contaService;

	@EJB
	private PessoaService pessoaService;
	
	@EJB
	private EnderecoService enderecoService;
	
	@EJB
	private CidadeService cidadeService;
	
	@EJB
	private UsuarioService usuarioService;
	
	@EJB
	private MovimentacaoService movimentacaoService;

	@Test
	public void consultarSaldo() {

		Cidade cidade = new Cidade();
		cidade.setEstado(Estado.PARANA);
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
		
		ContaCorrente conta = new ContaCorrente();
		conta.setNumero("3334-3");
		conta.setAgencia("333334-3");
		conta.setSaldo(new BigDecimal("1000.00"));
		conta.setPessoa(clienteSaved);
		
		Conta contaSaved = contaService.save(conta);

		Conta contaConsultado = contaService.consultarSaldo(contaSaved);
		
		assertThat(contaConsultado.getSaldo(), equalTo(new BigDecimal("1000.00")));
	}
	
	@Test
	public void debitar() {
		
		Cidade cidade = new Cidade();
		cidade.setEstado(Estado.PARANA);
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
		
		Conta conta = new Conta();
		conta.setNumero("3335-3");
		conta.setAgencia("333335-3");
		conta.setSaldo(new BigDecimal("1000.00"));
		conta.setPessoa(clienteSaved);
		
		Conta contaOrigemSaved = contaService.save(conta);
		Conta contaDestinoSaved = contaService.save(new Conta());
		
		BigDecimal actual = contaService.debitar(new BigDecimal("55.99"), contaOrigemSaved, contaDestinoSaved);

		Conta contaOrigemConsultado = contaService.consultarSaldo(contaOrigemSaved);
		
		assertThat(actual, equalTo(contaOrigemConsultado.getSaldo()));
		
		List<Movimentacao> movimentacaoDebitos = movimentacaoService.somenteDebitos(contaOrigemConsultado);
		
		assertThat(movimentacaoDebitos.stream().findFirst().get().getValor(), equalTo(new BigDecimal("55.99")));
	}
	
	@Test(expected = RuntimeException.class)
	public void debitarSemSaldoSuficiente() {
		
		Cidade cidade = new Cidade();
		cidade.setEstado(Estado.PARANA);
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
		
		Conta conta = new Conta();
		conta.setNumero("3333-3");
		conta.setAgencia("333333-3");
		conta.setSaldo(new BigDecimal("1000.00"));
		conta.setPessoa(clienteSaved);
		
		Conta contaOrigemSaved = contaService.save(conta);
		Conta contaDestinoSaved = contaService.save(new Conta());
		
		contaService.debitar(new BigDecimal("1055.99"), contaOrigemSaved, contaDestinoSaved);
	}
	
	@Test
	public void depositar() {
		
		Cidade cidade = new Cidade();
		cidade.setEstado(Estado.PARANA);
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
		
		Conta conta = new Conta();
		conta.setNumero("3333-3");
		conta.setAgencia("333333-3");
		conta.setSaldo(new BigDecimal("1000.00"));
		conta.setPessoa(clienteSaved);
		
		Conta contaOrigemSaved = contaService.save(conta);
		Conta contaDestinoSaved = contaService.save(new Conta());
		
		BigDecimal actual = contaService.debitar(new BigDecimal("55.99"), contaOrigemSaved, contaDestinoSaved);
		
		Conta contaConsultado = contaService.consultarSaldo(contaOrigemSaved);
		
		assertThat(actual, equalTo(contaConsultado.getSaldo()));
	}
	
}

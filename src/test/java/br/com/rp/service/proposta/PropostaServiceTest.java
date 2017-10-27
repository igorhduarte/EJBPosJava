package br.com.rp.service.proposta;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Objects;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cidade;
import br.com.rp.domain.Endereco;
import br.com.rp.domain.Estado;
import br.com.rp.domain.Pessoa;
import br.com.rp.domain.Proposta;
import br.com.rp.domain.TipoUsuario;
import br.com.rp.domain.Usuario;
import br.com.rp.services.proposta.PropostaService;
import br.com.rp.services.usuario.CidadeService;
import br.com.rp.services.usuario.EnderecoService;
import br.com.rp.services.usuario.PessoaService;
import br.com.rp.services.usuario.UsuarioService;

public class PropostaServiceTest extends AbstractTest {

	@EJB
	private PropostaService service;

	@EJB
	private PessoaService pessoaService;
	
	@EJB
	private EnderecoService enderecoService;
	
	@EJB
	private CidadeService cidadeService;
	
	@EJB
	private UsuarioService usuarioService;
	
	@Test
	public void salvarPropostas() {

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
		
		Pessoa pessoaSaved = pessoaService.save(pessoa);

		Proposta proposta = new Proposta();
		proposta.setPessoa(pessoaSaved);
		proposta.setLimite(new BigDecimal("10.10"));
		proposta.setSalario(new BigDecimal("1000.00"));
		
		Usuario usuarioLogado = new Usuario();
		usuarioLogado.setTipoUsuario(TipoUsuario.CLIENTE);
		
		Proposta actual = service.criarProposta(usuarioLogado , proposta);
		
		assertTrue(Objects.nonNull(actual.getId()));
		
	}
	
	@Test(expected = RuntimeException.class)
	public void aceitarPropostaSemPermissao() {

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
		
		Pessoa pessoaSaved = pessoaService.save(pessoa);

		Proposta proposta = new Proposta();
		proposta.setPessoa(pessoaSaved);
		proposta.setLimite(new BigDecimal("10.10"));
		proposta.setSalario(new BigDecimal("1000.00"));
		
		Usuario usuarioProposta = new Usuario();
		usuarioProposta.setTipoUsuario(TipoUsuario.CLIENTE);
		
		Proposta propostaSaved = service.criarProposta(usuarioProposta , proposta);

		Usuario usuarioLogado = new Usuario();
		usuarioLogado.setTipoUsuario(TipoUsuario.CLIENTE);
		
		service.aceitarProposta(usuarioLogado, propostaSaved);
		
	}
	
	@Test
	public void aceitarPropostas() {
		
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
		
		Pessoa pessoaSaved = pessoaService.save(pessoa);
		
		Proposta proposta = new Proposta();
		proposta.setPessoa(pessoaSaved);
		proposta.setLimite(new BigDecimal("10.10"));
		proposta.setSalario(new BigDecimal("1000.00"));
		
		Usuario usuarioProposta = new Usuario();
		usuarioProposta.setTipoUsuario(TipoUsuario.CLIENTE);
		
		Proposta propostaSaved = service.criarProposta(usuarioProposta , proposta);
		
		Usuario usuarioLogado = new Usuario();
		usuarioLogado.setTipoUsuario(TipoUsuario.GERENTE_CONTAS);
		
		service.aceitarProposta(usuarioLogado, propostaSaved);
		
		Usuario actual = usuarioService.getUsuarioByLogin(pessoaSaved.getCpf());
		
		assertTrue(Objects.nonNull(actual.getSenha()));
		assertThat(actual.getLogin(), equalTo(pessoaSaved.getCpf()));
		assertThat(actual.getPessoa().getCpf(), equalTo(pessoaSaved.getCpf()));
		assertThat(actual.getPessoa().getEmail(), equalTo(pessoaSaved.getEmail()));
		assertThat(actual.getTipoUsuario(), equalTo(TipoUsuario.CLIENTE));
		
	}
	

}

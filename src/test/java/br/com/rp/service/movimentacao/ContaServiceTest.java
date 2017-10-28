package br.com.rp.service.movimentacao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.ejb.EJB;

import org.junit.Test;

import br.com.rp.AbstractTest;
import br.com.rp.domain.Cidade;
import br.com.rp.domain.Configuracao;
import br.com.rp.domain.Conta;
import br.com.rp.domain.Endereco;
import br.com.rp.domain.Estado;
import br.com.rp.domain.Pessoa;
import br.com.rp.domain.TipoUsuario;
import br.com.rp.domain.Usuario;
import br.com.rp.services.LogService;
import br.com.rp.services.movimentacao.ConfiguracaoService;
import br.com.rp.services.movimentacao.ContaService;
import br.com.rp.services.movimentacao.MovimentacaoService;
import br.com.rp.services.usuario.CidadeService;
import br.com.rp.services.usuario.EnderecoService;
import br.com.rp.services.usuario.PessoaService;
import br.com.rp.services.usuario.UsuarioService;

public class ContaServiceTest extends AbstractTest {
	
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
	
	@EJB
	private ConfiguracaoService configuracaoService;

	@EJB
	private LogService logService;

	@Test
	public void depositar() {

		Configuracao configuracao = new Configuracao();
		configuracao.setId(1L);
		configuracao.setHorarioMinimoMovimentacao(Calendar.getInstance());
		configuracao.setHorarioMaximoMovimentacao(Calendar.getInstance());
		
		configuracaoService.save(configuracao);
		
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
		
		Pessoa clienteDestino = new Pessoa();
		clienteDestino.setCpf("00000000000");
		clienteDestino.setEndereco(enderecoSaved);
		clienteDestino.setNome("DESTINO");

		Usuario usuario = new Usuario();
		usuario.setLogin(pessoa.getCpf());
		usuario.setSenha("123");
		usuario.setTipoUsuario(TipoUsuario.CLIENTE);
		usuario.setPessoa(pessoa);
		
		
		BigDecimal actual = contaService.debitar(usuarioService.criarUsuario(usuario), new BigDecimal("55.99"), contaOrigemSaved, "codigo 123456");
		
		Conta contaConsultado = contaService.consultarSaldo(contaOrigemSaved);
		
		assertThat(actual, equalTo(contaConsultado.getSaldo()));
		
	}
	
}

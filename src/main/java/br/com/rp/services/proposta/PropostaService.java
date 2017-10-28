package br.com.rp.services.proposta;

import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Proposta;
import br.com.rp.domain.TipoUsuario;
import br.com.rp.domain.Usuario;
import br.com.rp.repository.PropostaRepository;
import br.com.rp.repository.UsuarioRepository;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class PropostaService {

	@EJB
	private PropostaRepository propostaRepository;

	@EJB
	private UsuarioRepository usuarioRepository;

	@Interceptors(PropostaInterceptor.class)
	public List<Proposta> listarPropostas(Usuario usuario) {

		return propostaRepository.getAll();
	}
	
	@Interceptors({LogInterceptor.class, PropostaInterceptor.class})
	public List<Proposta> listarPropostasAceitas(Usuario usuarioLogado) {
		
		return propostaRepository.findPropostasAceitas();
	}
	
	@Interceptors({LogInterceptor.class, PropostaInterceptor.class})
	public List<Proposta> listarPropostasRejeitadas(Usuario usuarioLogado) {
		
		return propostaRepository.findPropostasRejeitadas();
	}

	@Interceptors(LogInterceptor.class)
	public Proposta criarProposta(Usuario usuarioLogado, Proposta proposta) {

		return propostaRepository.save(proposta);
	}

	public String geradorSenha() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	@Interceptors({LogInterceptor.class, PropostaInterceptor.class})
	public void aceitarProposta(Usuario usuarioLogado, Proposta proposta) {

		Usuario usuario = new Usuario();
		usuario.setPessoa(proposta.getPessoa());
		usuario.setTipoUsuario(TipoUsuario.CLIENTE);
		usuario.setLogin(proposta.getPessoa().getCpf());
		usuario.setSenha(geradorSenha());

		try {
			proposta.setAceita(Boolean.TRUE);
			propostaRepository.save(proposta);
			usuarioRepository.save(usuario);
			
			StringBuilder msg = new StringBuilder("Bem vindo ao VBank ");
			msg.append(proposta.getPessoa().getNome() + ", sua proposta foi aceita!");
			msg.append(" Seu login é " + usuario.getLogin());
			msg.append(" Sua senha é " + usuario.getSenha());

			PropostaEnvioEmail email = new PropostaEnvioEmail();
			email.send(proposta.getPessoa().getEmail(), "Bem vindo ao VBank", msg.toString());
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Interceptors(LogInterceptor.class)
	public void rejeitarProposta(Proposta proposta, String justificativa) {

		StringBuilder msg = new StringBuilder("Sua proposta não foi aceita ");
		msg.append(justificativa);

		PropostaEnvioEmail email = new PropostaEnvioEmail();
		email.send(proposta.getPessoa().getEmail(), "Bem vindo ao VBank", msg.toString());
		
		try {
			proposta.setAceita(Boolean.FALSE);
			propostaRepository.save(proposta);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

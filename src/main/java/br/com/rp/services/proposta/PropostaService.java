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
	
	@Interceptors(PropostaInterceptor.class)
	public List<Proposta> listarPropostasAceitas(Usuario usuarioLogado) {
		
		return propostaRepository.findPropostasAceitas();
	}
	
	@Interceptors(PropostaInterceptor.class)
	public List<Proposta> listarPropostasRejeitadas(Usuario usuarioLogado) {
		
		return propostaRepository.findPropostasRejeitadas();
	}

	public Proposta criarProposta(Usuario usuarioLogado, Proposta proposta) {

		return propostaRepository.save(proposta);
	}

	public String geradorSenha() {
		return UUID.randomUUID().toString().substring(0, 8);
	}
	
	@Interceptors(PropostaInterceptor.class)
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
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void rejeitarProposta(Proposta proposta) {

		Usuario usuario = new Usuario();
//		usuario.setPessoa(proposta.getCliente());

		try {
			proposta.setAceita(Boolean.FALSE);
			propostaRepository.save(proposta);
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

package br.com.rp.services.proposta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Proposta;
import br.com.rp.domain.Usuario;
import br.com.rp.repository.proposta.PropostaRepository;
import br.com.rp.repository.usuario.UsuarioRepository;

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
	public List<Proposta> listarPropostasAceitas(Usuario usuario) {
		
		return propostaRepository.findPropostasAceitas();
	}
	
	@Interceptors(PropostaInterceptor.class)
	public List<Proposta> listarPropostasRejeitadas(Usuario usuario) {
		
		return propostaRepository.findPropostasRejeitadas();
	}

	public Proposta criarProposta(Proposta proposta) {

		return propostaRepository.save(proposta);
	}

	public void aceitarProposta(Proposta proposta) {

		Usuario usuario = new Usuario();
		usuario.setPessoa(proposta.getConta().getCliente().getPessoa());

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
		usuario.setPessoa(proposta.getConta().getCliente().getPessoa());

		try {
			proposta.setAceita(Boolean.FALSE);
			propostaRepository.save(proposta);
			usuarioRepository.save(usuario);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}

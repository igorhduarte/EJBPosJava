package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import br.com.rp.domain.Usuario;
import br.com.rp.repository.UsuarioRepository;
import br.com.rp.repository.impl.LogInterceptor;

@Stateless
public class UsuarioService {

	@EJB
	private UsuarioRepository repository;

	@Interceptors(LogInterceptor.class)
	public Usuario criarUsuario(Usuario usuario) {

		return repository.save(usuario);
	}

	@Interceptors(LogInterceptor.class)
	public Usuario getUsuarioByLogin(String login) {
		
		return repository.getUsuarioByLogin(login);
	}

}

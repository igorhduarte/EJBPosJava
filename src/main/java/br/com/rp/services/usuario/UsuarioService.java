package br.com.rp.services.usuario;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.rp.domain.Usuario;
import br.com.rp.repository.UsuarioRepository;

@Stateless
public class UsuarioService {

	@EJB
	private UsuarioRepository repository;

	public Usuario criarUsuario(Usuario usuario) {

		return repository.save(usuario);
	}
	
	public Usuario getUsuarioByLogin(String login) {
		
		return repository.getUsuarioByLogin(login);
	}

}

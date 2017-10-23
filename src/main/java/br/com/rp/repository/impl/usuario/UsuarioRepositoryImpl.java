package br.com.rp.repository.impl.usuario;

import javax.ejb.Stateless;

import br.com.rp.domain.Usuario;
import br.com.rp.repository.impl.AbstractRepositoryImpl;
import br.com.rp.repository.usuario.UsuarioRepository;

@Stateless
public class UsuarioRepositoryImpl extends AbstractRepositoryImpl<Usuario> implements UsuarioRepository {

	public UsuarioRepositoryImpl() {
		super(Usuario.class);
	}

}

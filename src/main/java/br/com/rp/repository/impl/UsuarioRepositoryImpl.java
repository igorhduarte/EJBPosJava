package br.com.rp.repository.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.rp.domain.Usuario;
import br.com.rp.repository.UsuarioRepository;

@Stateless
public class UsuarioRepositoryImpl extends AbstractRepositoryImpl<Usuario> implements UsuarioRepository {

	@PersistenceContext(unitName = "vbankpu")
	private EntityManager em;

	public UsuarioRepositoryImpl() {
		super(Usuario.class);
	}

	public Usuario getUsuarioByLogin(String login) {
		
		return (Usuario) em.createQuery("from Usuario u where u.login = " + login).getSingleResult();
	}

}

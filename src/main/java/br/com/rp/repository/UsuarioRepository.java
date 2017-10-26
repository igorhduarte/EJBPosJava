package br.com.rp.repository;


import br.com.rp.domain.Usuario;

public interface UsuarioRepository extends Repository<Usuario> {

	Usuario getUsuarioByLogin(String login);
	
}

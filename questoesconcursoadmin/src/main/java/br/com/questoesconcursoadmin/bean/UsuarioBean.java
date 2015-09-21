package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.UsuarioDao;
import br.com.questoesconcursoadmin.model.Usuario;
import br.com.questoesconcursoadmin.remote.UsuarioRemote;

@Stateless
@LocalBean
public class UsuarioBean extends GenericBean<Usuario, Integer> implements UsuarioRemote{
	
	@EJB
    private UsuarioDao usuarioDao;

	@Override
	public List<Usuario> listarTodos() {
		
		return usuarioDao.findAll();
	}

	@Override
	public Usuario recuperarPorEmail(String email) {
		return usuarioDao.recuperarPorEmail(email);
	}
}

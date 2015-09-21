package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.UsuarioPerfilDao;
import br.com.questoesconcursoadmin.model.UsuarioPerfil;
import br.com.questoesconcursoadmin.remote.UsuarioPerfilRemote;

@Stateless
@LocalBean
public class UsuarioPerfilBean extends GenericBean<UsuarioPerfil, Integer> implements UsuarioPerfilRemote{

	@EJB UsuarioPerfilDao usuarioPerfilDAO;
	
	@Override
	public List<UsuarioPerfil> findByCodigoUsuario(Integer codigoUsuario) {
		
		return usuarioPerfilDAO.findByCodigoUsuario(codigoUsuario);
	}
	
}

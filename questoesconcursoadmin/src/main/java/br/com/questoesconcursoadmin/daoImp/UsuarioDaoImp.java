package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.questoesconcursoadmin.dao.UsuarioDao;
import br.com.questoesconcursoadmin.model.Usuario;

@Stateless
@LocalBean
public class UsuarioDaoImp extends GenericDAOJPAImpl<Usuario, Integer> implements UsuarioDao{
 
	@Override
	public Usuario recuperarPorEmail(String email) {
		Query query = em.createQuery("select usuario from Usuario usuario where usuario.email = :email");
		query.setParameter("email", email);
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario;
	}
}

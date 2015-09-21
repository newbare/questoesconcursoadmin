package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.questoesconcursoadmin.dao.UsuarioPerfilDao;
import br.com.questoesconcursoadmin.model.UsuarioPerfil;

@Stateless
@LocalBean
public class UsuarioPerfilDaoImp extends GenericDAOJPAImpl<UsuarioPerfil, Integer> implements UsuarioPerfilDao{

	@Override
	public void deleteByCodigoUsuario(Integer codigoUsuario)
			throws PersistenceException {
		Query query = em.createQuery("delete from UsuarioPerfil up where up.IdUsuario = :idUsuario");
		query.setParameter("idUsuario", codigoUsuario);
		query.executeUpdate();
	}

	@Override
	public List<UsuarioPerfil> findByCodigoUsuario(Integer codigoUsuario)
			throws PersistenceException {
		Query query = em.createQuery("select up from UsuarioPerfil up where up.IdUsuario = :idUsuario");
		query.setParameter("idUsuario", codigoUsuario);
		return query.getResultList();
	}

}

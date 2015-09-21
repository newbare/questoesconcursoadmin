package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.questoesconcursoadmin.dao.PerfilDao;
import br.com.questoesconcursoadmin.model.Perfil;

@Stateless
@LocalBean
public class PerfilDaoImp extends GenericDAOJPAImpl<Perfil, Integer> implements PerfilDao{

	@Override
	public Perfil findByAuthority(String authority) {
		Query query = em.createQuery("select perfil from Perfil perfil where perfil.authority = :authority");
		query.setParameter("authority", authority);
		Perfil perfil = (Perfil) query.getSingleResult();
		return perfil;
	}
 
}

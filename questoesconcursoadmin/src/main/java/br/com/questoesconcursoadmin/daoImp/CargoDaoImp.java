package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.CargoDao;
import br.com.questoesconcursoadmin.model.Cargo;

@Stateless
@LocalBean
public class CargoDaoImp extends GenericDAOJPAImpl<Cargo, Integer> implements CargoDao{

	@Override
	public List<Cargo> findByEntity(Cargo entity) throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Cargo.class, "c");
		if(entity.getNome() != null && !"".equals(entity.getNome().trim())){
			c.add(Restrictions.ilike("c.nome", entity.getNome(), MatchMode.ANYWHERE));
		}
		
		List<Cargo> lista = (List<Cargo>) c.list();
		return lista;
	}
	
}

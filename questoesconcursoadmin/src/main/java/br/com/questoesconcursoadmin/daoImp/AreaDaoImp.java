package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.AreaDao;
import br.com.questoesconcursoadmin.model.Area;

@Stateless
@LocalBean
public class AreaDaoImp extends GenericDAOJPAImpl<Area, Integer> implements AreaDao{

	@Override
	public List<Area> findByEntity(Area entity) throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Area.class, "a");
		if(entity.getArea() != null && !"".equals(entity.getArea().trim())){
			c.add(Restrictions.ilike("a.area", entity.getArea(), MatchMode.ANYWHERE));
		}
		
		List<Area> lista = (List<Area>) c.list();
		return lista;
	}
	
}

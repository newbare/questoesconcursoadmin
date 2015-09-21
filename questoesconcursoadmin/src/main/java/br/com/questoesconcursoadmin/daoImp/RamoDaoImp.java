package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.RamoDao;
import br.com.questoesconcursoadmin.model.Ramo;

@Stateless
@LocalBean
public class RamoDaoImp extends GenericDAOJPAImpl<Ramo, Integer> implements RamoDao{

	@Override
	public List<Ramo> findByEntity(Ramo entity) throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Ramo.class, "r");
		if(entity.getRamo() != null && !"".equals(entity.getRamo().trim())){
			c.add(Restrictions.ilike("r.ramo", entity.getRamo(), MatchMode.ANYWHERE));
		}
		
		if(entity.getEspecialidade() != null && entity.getEspecialidade().getId() != null){
			c.add(Restrictions.eq("r.especialidade", entity.getEspecialidade()));
		}
		
		List<Ramo> lista = (List<Ramo>) c.list();
		return lista;
	}
	
}

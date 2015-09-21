package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.AlternativaDao;
import br.com.questoesconcursoadmin.model.Alternativa;

@Stateless
@LocalBean
public class AlternativaDaoImp extends GenericDAOJPAImpl<Alternativa, Long> implements AlternativaDao{
	
	@Override
	public List<Alternativa> findByEntity(Alternativa entity)throws PersistenceException {
		
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Alternativa.class, "e");
		
		if(entity.getId() != null && entity.getId() > 0){
			c.add(Restrictions.eq("e.id", entity.getId()));
		}
		
		if(entity.getQuestao()!= null && entity.getQuestao().getId() != null){
			c.add(Restrictions.eq("e.questao", entity.getQuestao()));
		}
		
		List<Alternativa> lista = (List<Alternativa>) c.list();
		return lista;
	}

	@Override
	public Alternativa findOneByEntity(Alternativa entity)
			throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Alternativa.class, "e");
		
		if(entity.getId() != null && entity.getId() > 0){
			c.add(Restrictions.eq("e.id", entity.getId()));
		}
		
		if(entity.getQuestao()!= null && entity.getQuestao().getId() != null){
			c.add(Restrictions.eq("e.questao", entity.getQuestao()));
		}
		
		return (Alternativa) c.uniqueResult();
	}

}

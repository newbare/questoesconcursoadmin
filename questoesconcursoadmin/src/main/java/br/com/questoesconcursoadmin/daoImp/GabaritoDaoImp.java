package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.GabaritoDao;
import br.com.questoesconcursoadmin.model.Gabarito;

@Stateless
@LocalBean
public class GabaritoDaoImp extends GenericDAOJPAImpl<Gabarito, Long> implements GabaritoDao{

	@Override
	public void deleteByQuestion(Long questao) throws PersistenceException{
		
		Query query = em.createQuery("delete from Gabarito g where g.idQuestao = :idQuestao");
		query.setParameter("idQuestao", questao);
		query.executeUpdate();
		
	}

	@Override
	public Gabarito findGabaritoByQuestion(Long questao) throws PersistenceException {
		
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Gabarito.class, "g");
		if(questao != null && questao > 0){
			c.add(Restrictions.eq("g.idQuestao", questao));
		}
		
		return (Gabarito) c.uniqueResult();
	}

}

package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.BancaDao;
import br.com.questoesconcursoadmin.model.Banca;

@Stateless
@LocalBean
public class BancaDaoImp extends GenericDAOJPAImpl<Banca, Integer> implements BancaDao{
	
	@Override
	public List<Banca> findByEntity(Banca entity) throws PersistenceException {

		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Banca.class, "b");
		if(entity.getNome() != null && !"".equals(entity.getNome().trim())){
			c.add(Restrictions.ilike("b.nome", entity.getNome(), MatchMode.ANYWHERE));
		}
		
		if(entity.getAbreviacao() != null && !"".equals(entity.getAbreviacao().trim())){
			c.add(Restrictions.ilike("b.abreviacao", entity.getAbreviacao(), MatchMode.ANYWHERE));
		}
		
		List<Banca> lista = (List<Banca>) c.list();
		return lista;
	}

}

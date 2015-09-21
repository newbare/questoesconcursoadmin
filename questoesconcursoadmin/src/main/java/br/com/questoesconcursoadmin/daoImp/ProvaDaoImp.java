package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.ProvaDao;
import br.com.questoesconcursoadmin.model.Prova;

@Stateless
@LocalBean
public class ProvaDaoImp extends GenericDAOJPAImpl<Prova, Integer> implements ProvaDao{

	@Override
	public List<Prova> findByEntity(Prova entity) throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Prova.class, "p");
		if(entity.getData() != null){
			c.add(Restrictions.sqlRestriction("CAST(pro_data as VARCHAR) like '%" + entity.getData() + "%'"));
		}
		
		if(entity.getCargoConcursoArea() != null && entity.getCargoConcursoArea() > 0){
			c.add(Restrictions.eq("p.cargoConcursoArea", entity.getCargoConcursoArea()));
		}
		
		if(entity.getConcluida() != null){
			c.add(Restrictions.eq("p.concluida", entity.getConcluida()));
		}
		
		List<Prova> lista = (List<Prova>) c.list();
		return lista;
	}

	@Override
	public Prova findyByCCAId(Integer idCCA) {
		Query query = em.createQuery("select p from Prova p where p.cargoConcursoArea = :idCCA and p.concluida = false");
		query.setParameter("idCCA", idCCA);
		List<Object> resultado = query.getResultList();
		if(resultado != null && resultado.size() > 0){
			return (Prova) resultado.get(0);
		}
		return null;
	}
	
}

package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.ConcursoDao;
import br.com.questoesconcursoadmin.model.Concurso;

@Stateless
@LocalBean
public class ConcursoDaoImp extends GenericDAOJPAImpl<Concurso, Long> implements ConcursoDao{
	
	@Override
	public List<Concurso> findByEntity(Concurso entity) throws PersistenceException {

		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Concurso.class, "e");
		
		if(entity.getOrgao() != null && entity.getOrgao().getId() != null){
			c.add(Restrictions.eq("e.orgao", entity.getOrgao()));
		}
		
		if(entity.getBanca() != null && entity.getBanca().getId() != null){
			c.add(Restrictions.eq("e.banca", entity.getBanca()));
		}
		
		if(entity.getAno() != null && !"".equals(entity.getAno())){
			c.add(Restrictions.eq("e.ano", entity.getAno()));
		}
		
		List<Concurso> lista = (List<Concurso>) c.list();
		return lista;
	}
	
}

package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.DisciplinaDao;
import br.com.questoesconcursoadmin.model.Disciplina;

@Stateless
@LocalBean
public class DisciplinaDaoImp extends GenericDAOJPAImpl<Disciplina, Integer> implements DisciplinaDao{

	@Override
	public List<Disciplina> findByEntity(Disciplina entity) throws PersistenceException {

		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Disciplina.class, "e");
		if(entity.getNome() != null && !"".equals(entity.getNome().trim())){
			c.add(Restrictions.ilike("e.nome", entity.getNome(), MatchMode.ANYWHERE));
		}
		
		List<Disciplina> lista = (List<Disciplina>) c.list();
		return lista;
	}
	
}

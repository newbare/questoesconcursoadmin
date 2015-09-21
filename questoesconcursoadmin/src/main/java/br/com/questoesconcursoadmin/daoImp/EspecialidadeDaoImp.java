package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.EspecialidadeDao;
import br.com.questoesconcursoadmin.model.Especialidade;

@Stateless
@LocalBean
public class EspecialidadeDaoImp extends GenericDAOJPAImpl<Especialidade, Integer> implements EspecialidadeDao{

	@Override
	public List<Especialidade> findByEntity(Especialidade entity) throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Especialidade.class, "e");
		if(entity.getDescricao() != null && !"".equals(entity.getDescricao().trim())){
			c.add(Restrictions.ilike("e.descricao", entity.getDescricao(), MatchMode.ANYWHERE));
		}
		
		List<Especialidade> lista = (List<Especialidade>) c.list();
		return lista;
	}
	
}

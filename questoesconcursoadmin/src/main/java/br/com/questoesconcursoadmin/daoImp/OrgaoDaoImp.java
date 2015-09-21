package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.OrgaoDao;
import br.com.questoesconcursoadmin.model.Orgao;

@Stateless
@LocalBean
public class OrgaoDaoImp extends GenericDAOJPAImpl<Orgao, Long> implements OrgaoDao{
	
	@Override
	public List<Orgao> findByEntity(Orgao entity) throws PersistenceException {
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Orgao.class, "o");
		if(entity.getOrgao() != null && !"".equals(entity.getOrgao().trim())){
			c.add(Restrictions.ilike("o.orgao", entity.getOrgao(), MatchMode.ANYWHERE));
		}
		
		if(entity.getOrgaoAbreviacao() != null && !"".equals(entity.getOrgaoAbreviacao().trim())){
			c.add(Restrictions.ilike("o.orgaoAbreviacao", entity.getOrgaoAbreviacao(), MatchMode.ANYWHERE));
		}
		
		List<Orgao> lista = (List<Orgao>) c.list();
		return lista;
	}
	
}

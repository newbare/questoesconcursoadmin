package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.CategoriaDao;
import br.com.questoesconcursoadmin.model.Categoria;

@Stateless
@LocalBean
public class CategoriaDaoImp extends GenericDAOJPAImpl<Categoria, Integer> implements CategoriaDao{
	
	@Override
	public List<Categoria> findByEntity(Categoria entity) throws PersistenceException {

		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Categoria.class, "e");
		if(entity.getNome() != null && !"".equals(entity.getNome().trim())){
			c.add(Restrictions.ilike("e.nome", entity.getNome(), MatchMode.ANYWHERE));
		}
		
		if(entity.getDisciplina()!= null && entity.getDisciplina().getId() != null){
			c.add(Restrictions.eq("e.disciplina", entity.getDisciplina()));
		}
		
		List<Categoria> lista = (List<Categoria>) c.list();
		return lista;
	}
	
}

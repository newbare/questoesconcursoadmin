package br.com.questoesconcursoadmin.daoImp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaQuery;

import br.com.questoesconcursoadmin.dao.GenericDAO;

@Stateless
@LocalBean
public class GenericDAOJPAImpl<T, ID extends Serializable> implements
		GenericDAO<T, ID> {

	private Class<T> entityClass;

	@PersistenceContext(unitName = "CrudPU")
	public EntityManager em;

	
	
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		if (entityClass == null) {
			ParameterizedType type = (ParameterizedType) getClass()
					.getGenericSuperclass();
			Object o = type.getActualTypeArguments()[0];
			this.entityClass = (Class<T>) o;
		}
		return entityClass;
	}

	@Override
	public T update(T entity) throws PersistenceException {
		return em.merge(entity);
	}

	@Override
	public T findById(ID id, boolean lock) throws PersistenceException {
		return em.find(entityClass, id);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<T> findAll() throws PersistenceException {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();

	}

	@Override
	public void insert(T entity) throws PersistenceException {
		em.persist(entity);
	}

	@Override
	public void delete(T entity) throws PersistenceException {
		T entityToBeRemoved = em.merge(entity);
		em.remove(entityToBeRemoved);
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public T merge(T entity) throws PersistenceException {
		return em.merge(entity);
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<T> findByEntity(T entity) throws PersistenceException {
		
		return null;
	}

}

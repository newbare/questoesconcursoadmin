package br.com.questoesconcursoadmin.bean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.questoesconcursoadmin.dao.GenericDAOCRUD;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.BaseDomain;
import br.com.questoesconcursoadmin.remote.GenericRemote;


/**
 * Session Bean implementation class GenericBean
 */

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.BEAN)
public class GenericBean<T extends BaseDomain, ID extends Serializable> implements GenericRemote<T, ID> {

	@EJB GenericDAOCRUD daocrud;
	
	private Class<T> entityClass;

    
	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		ParameterizedType type =  (ParameterizedType)getClass().getGenericSuperclass();
		Object o = type.getActualTypeArguments()[0];
		this.entityClass = (Class<T>)o;
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	public T recuperar(ID id) {
		daocrud.setEntityClass(getEntityClass());
		Integer i_id = (Integer)id;
		T t = (T)daocrud.findById(i_id, false);
		return t;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> recuperarTodos(){
		daocrud.setEntityClass(getEntityClass());
		List<T> list = (List<T>)daocrud.findAll();
		return list;
	}	

	public T inserir(T entity){
		daocrud.insert(entity);
		return entity;
	}
	
	public void alterar(T entity){
		daocrud.update(entity);
	}

	public void deletar(T entity){
		daocrud.delete(entity);
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> recuperarPorEntidade(T entity) throws BusinessException {
		return (List<T>) daocrud.findByEntity(entity);
	}

	@Override
	public List<T> inserir(List<T> lista) throws BusinessException {
		for(T t : lista){
			daocrud.insert(t);
		}
		return lista;
	}
	
	public void alterar(List<T> lista){
		for(T entity : lista){
			daocrud.update(entity);
		}
	}
}

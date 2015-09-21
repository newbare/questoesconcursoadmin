package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.BaseDomain;


@Local
public interface GenericDAOCRUD extends GenericDAO<BaseDomain, Integer>  {
	
	@SuppressWarnings("rawtypes")
	public void setEntityClass(Class clazz);

}

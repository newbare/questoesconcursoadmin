package br.com.questoesconcursoadmin.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;


public interface GenericDAO<T, ID extends Serializable> {
	
	 T findById(ID id, boolean lock) throws PersistenceException;
	 
	 List<T> findAll() throws PersistenceException;
	 
	 void insert(T entity) throws PersistenceException;
	 
	 T update(T entity) throws PersistenceException;
	 
	 void delete(T entity) throws PersistenceException;
	 
	 T merge(T entity) throws PersistenceException;
	 
	 EntityManager getEntityManager();
	 
	 List<T> findByEntity(T entity) throws PersistenceException;
	 
}
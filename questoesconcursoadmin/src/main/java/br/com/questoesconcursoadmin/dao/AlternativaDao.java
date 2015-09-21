package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.model.Alternativa;

@Local
public interface AlternativaDao extends GenericDAO<Alternativa, Long>{
	
	public Alternativa findOneByEntity(Alternativa entity)throws PersistenceException;
	
}

package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.model.Gabarito;

@Local
public interface GabaritoDao extends GenericDAO<Gabarito, Long>{
	
	public void deleteByQuestion(Long questao) throws PersistenceException;
	
	public Gabarito findGabaritoByQuestion(Long questao) throws PersistenceException;
	
}

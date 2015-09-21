package br.com.questoesconcursoadmin.dao;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.model.Questao;

@Local
public interface QuestaoDao extends GenericDAO<Questao, Long>{
	
	public List<Questao> findByEntityQuery(Questao entity)throws PersistenceException;
	
}

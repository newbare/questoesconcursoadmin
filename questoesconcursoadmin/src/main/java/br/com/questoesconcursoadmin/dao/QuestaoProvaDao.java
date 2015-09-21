package br.com.questoesconcursoadmin.dao;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.model.QuestaoProva;

@Local
public interface QuestaoProvaDao extends GenericDAO<QuestaoProva, Long>{
	
	public void deleteByCodigoQuestao(Long codigoQuestao) throws PersistenceException;
	
	public void deleteByCodigoProva(Long codigoProva) throws PersistenceException;
	
	public List<QuestaoProva> findByCodigoQuestao(Long codigoQuestao) throws PersistenceException;
	
	public List<QuestaoProva> findByCodigoProva(Long codigoProva) throws PersistenceException;
	
}

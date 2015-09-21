package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.QuestaoComentario;

@Local
public interface QuestaoComentarioDao extends GenericDAO<QuestaoComentario, Integer>{
	
}

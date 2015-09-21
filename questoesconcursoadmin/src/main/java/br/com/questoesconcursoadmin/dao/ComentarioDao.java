package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Comentario;

@Local
public interface ComentarioDao extends GenericDAO<Comentario, Integer>{
	
	
}

package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Concurso;

@Local
public interface ConcursoDao extends GenericDAO<Concurso, Long>{
	
	
}

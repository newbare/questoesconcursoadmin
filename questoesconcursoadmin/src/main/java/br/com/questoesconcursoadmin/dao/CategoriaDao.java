package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Categoria;

@Local
public interface CategoriaDao extends GenericDAO<Categoria, Integer>{
	
	
}

package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Prova;

@Local
public interface ProvaDao extends GenericDAO<Prova, Integer>{
	
	public Prova findyByCCAId(Integer idCCA);
}

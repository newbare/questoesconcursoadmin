package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Cargo;

@Local
public interface CargoDao extends GenericDAO<Cargo, Integer>{
	
	
}

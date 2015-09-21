package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Perfil;

@Local
public interface PerfilDao extends GenericDAO<Perfil, Integer>{
	
	public Perfil findByAuthority(String authority);
	
}

package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Orgao;

@Local
public interface OrgaoDao extends GenericDAO<Orgao, Long>{
	
}

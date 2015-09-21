package br.com.questoesconcursoadmin.dao;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.model.Pessoa;

@Local
public interface PessoaDao extends GenericDAO<Pessoa, Integer>{
	
	List<Pessoa> findByEntity(Pessoa pessoa) throws PersistenceException;
	
}

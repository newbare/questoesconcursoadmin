package br.com.questoesconcursoadmin.dao;

import java.util.List;

import javax.ejb.Local;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.model.UsuarioPerfil;

@Local
public interface UsuarioPerfilDao extends GenericDAO<UsuarioPerfil, Integer>{
	
	public void deleteByCodigoUsuario(Integer codigoUsuario) throws PersistenceException;
	
	public List<UsuarioPerfil> findByCodigoUsuario(Integer codigoUsuario) throws PersistenceException;
	
}

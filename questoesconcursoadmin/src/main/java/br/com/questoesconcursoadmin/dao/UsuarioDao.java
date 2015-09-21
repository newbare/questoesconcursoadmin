package br.com.questoesconcursoadmin.dao;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Usuario;

@Local
public interface UsuarioDao extends GenericDAO<Usuario, Integer>{
	
	public Usuario recuperarPorEmail(String email);

}

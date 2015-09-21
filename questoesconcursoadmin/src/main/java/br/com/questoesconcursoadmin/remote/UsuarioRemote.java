package br.com.questoesconcursoadmin.remote;

import java.util.List;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Usuario;

@Local
public interface UsuarioRemote extends GenericRemote<Usuario, Integer>{

	 public List<Usuario> listarTodos() throws BusinessException;
	 
	 public Usuario recuperarPorEmail(String email) throws BusinessException;
}

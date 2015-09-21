package br.com.questoesconcursoadmin.remote;

import java.util.List;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.UsuarioPerfil;

@Local
public interface UsuarioPerfilRemote extends GenericRemote<UsuarioPerfil, Integer>{
	
	public List<UsuarioPerfil> findByCodigoUsuario(Integer codigoUsuario) throws BusinessException;

}

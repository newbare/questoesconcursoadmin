package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Perfil;

@Local
public interface PerfilRemote extends GenericRemote<Perfil, Integer>{

}

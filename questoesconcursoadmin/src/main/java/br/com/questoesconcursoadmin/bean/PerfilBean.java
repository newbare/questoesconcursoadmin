package br.com.questoesconcursoadmin.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.model.Perfil;
import br.com.questoesconcursoadmin.remote.PerfilRemote;

@Stateless
@LocalBean
public class PerfilBean extends GenericBean<Perfil, Integer> implements PerfilRemote{
	
}

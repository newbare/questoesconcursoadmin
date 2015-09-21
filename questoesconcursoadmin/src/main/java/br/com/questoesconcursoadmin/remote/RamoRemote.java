package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Ramo;

@Local
public interface RamoRemote extends GenericRemote<Ramo, Integer>{
	
}

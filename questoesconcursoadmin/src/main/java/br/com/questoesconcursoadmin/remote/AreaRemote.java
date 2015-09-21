package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Area;

@Local
public interface AreaRemote extends GenericRemote<Area, Integer>{
	
}

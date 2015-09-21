package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Cargo;

@Local
public interface CargoRemote extends GenericRemote<Cargo, Integer>{

}

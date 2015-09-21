package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Banca;

@Local
public interface BancaRemote extends GenericRemote<Banca, Integer>{

}

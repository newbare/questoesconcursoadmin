package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Especialidade;

@Local
public interface EspecialidadeRemote extends GenericRemote<Especialidade, Long>{

}

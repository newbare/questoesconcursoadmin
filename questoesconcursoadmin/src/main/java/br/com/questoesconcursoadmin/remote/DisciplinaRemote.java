package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Disciplina;

@Local
public interface DisciplinaRemote extends GenericRemote<Disciplina, Integer>{

}

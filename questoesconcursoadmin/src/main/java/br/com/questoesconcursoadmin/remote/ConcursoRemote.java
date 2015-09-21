package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Concurso;

@Local
public interface ConcursoRemote extends GenericRemote<Concurso, Long>{

}

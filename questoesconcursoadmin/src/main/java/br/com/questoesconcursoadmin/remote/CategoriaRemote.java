package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Categoria;

@Local
public interface CategoriaRemote extends GenericRemote<Categoria, Integer>{

}

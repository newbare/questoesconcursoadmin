package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Prova;

@Local
public interface ProvaRemote extends GenericRemote<Prova, Integer>{
	
	public Prova findyByCCAId(Integer idCCA) throws BusinessException;
	
}

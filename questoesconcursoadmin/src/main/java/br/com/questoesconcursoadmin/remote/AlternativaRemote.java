package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Alternativa;

@Local
public interface AlternativaRemote extends GenericRemote<Alternativa, Long>{
	
	public Alternativa findOneByEntity(Alternativa entity) throws BusinessException;

}

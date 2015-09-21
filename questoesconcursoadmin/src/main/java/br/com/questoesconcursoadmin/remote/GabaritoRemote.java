package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Gabarito;

@Local
public interface GabaritoRemote extends GenericRemote<Gabarito, Long>{
	
	public Gabarito findGabaritoByQuestion(Long questao) throws BusinessException;

}

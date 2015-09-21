package br.com.questoesconcursoadmin.remote;

import java.util.List;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Pessoa;

@Local
public interface PessoaRemote extends GenericRemote<Pessoa, Integer>{
	
	public List<Pessoa> findByEntity(Pessoa pessoa) throws BusinessException;
	
}

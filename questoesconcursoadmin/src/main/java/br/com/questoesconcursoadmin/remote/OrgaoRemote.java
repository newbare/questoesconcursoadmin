package br.com.questoesconcursoadmin.remote;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Orgao;

@Local
public interface OrgaoRemote extends GenericRemote<Orgao, Long>{
	
}

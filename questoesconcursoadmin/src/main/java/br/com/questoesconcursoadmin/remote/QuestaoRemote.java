package br.com.questoesconcursoadmin.remote;

import java.util.List;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.Questao;

@Local
public interface QuestaoRemote extends GenericRemote<Questao, Long>{
	
	public List<Questao> findByEntityQuery(Questao entity);

}

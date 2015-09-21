package br.com.questoesconcursoadmin.servicos.generic;

import java.util.List;

import br.com.questoesconcursoadmin.model.Candidato;

/**
 * @ClassName ClassificaCandidatos
 * @Date 11/01/2014
 * 
 * @author Said
 */
public interface ClassificaCandidatosInterface {
	
	public List<Candidato> preparaListaCandidatos(String valores);
	
}

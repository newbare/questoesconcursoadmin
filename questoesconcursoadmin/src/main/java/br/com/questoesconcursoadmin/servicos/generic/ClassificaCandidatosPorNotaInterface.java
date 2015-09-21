package br.com.questoesconcursoadmin.servicos.generic;

import java.util.List;

import br.com.questoesconcursoadmin.model.Candidato;

/**
 * @ClassName ClassificaCandidatos
 * @Date 11/01/2014
 * 
 * @author Said
 */
public interface ClassificaCandidatosPorNotaInterface extends ClassificaCandidatosInterface{
	
	/**
	 * 
	 * Método que ordena as notas dos concursados que estejam disponíveis no formato:
	 * Matrícula, Nome, Nota da Prova, Nota da Redação / Matrícula, Nome, Nota da Prova, Nota da Redação /
	 * 
	 * Exemplo: 123456, Fulado de tal, 50.00, 18.45 / 654321, Ciclano de tal, 48.23, 22.3 / 
	 * 
	 * @param valores
	 */
	public List<Candidato> classificaCandidatos(String valores);
	
}

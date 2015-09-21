package br.com.questoesconcursoadmin.servicos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.questoesconcursoadmin.model.Candidato;
import br.com.questoesconcursoadmin.servicos.generic.ClassificaCandidatosPorNotaInterface;

public class ClassificaCandidatosPorNotaSemRedacao implements ClassificaCandidatosPorNotaInterface{

	@Override
	public List<Candidato> classificaCandidatos(String valores) {
		return preparaListaCandidatos(valores);
	}
	
	/**
	 * 
	 * Método que ordena as notas dos concursados que estejam disponíveis no formato:
	 * Matrícula, Nome, Nota da Prova / Matrícula, Nome, Nota da Prova /
	 * 
	 * Exemplo: 123456, Fulado de tal, 50.00 / 654321, Ciclano de tal, 48.23 / 
	 * 
	 * @param valores
	 */
	@Override
	public List<Candidato> preparaListaCandidatos(String valores){
		
		List<Candidato> candidatos = new ArrayList<Candidato>();
		
		int posicao = 0;
		int index = valores.indexOf(",");
		Candidato e = null;
		
		while(index != -1){
			e = new Candidato();
			for(int i = 0; i <= 3; i++){
				if(i == 0){
					e.setInscricao(valores.substring(posicao, index));
					valores = valores.substring(valores.indexOf(",") + 2, valores.length());
					index = valores.indexOf(",");
				}else if(i == 1){
					e.setNome(valores.substring(posicao, index));
					valores = valores.substring(valores.indexOf(",") + 2, valores.length());
					index = valores.indexOf("/") - 1;
				}else if(i == 2){
					e.setNota(Double.parseDouble(valores.substring(posicao, index)));
					if(valores.length() > valores.indexOf("/") + 2){
						valores = valores.substring(valores.indexOf("/") + 2, valores.length());
					}
					index = valores.indexOf(",");
				}
				
			}
			candidatos.add(e);
		}
		
		Collections.sort(candidatos);
		int cont = 1;
		for(Candidato ent: candidatos){
			System.out.println(cont + " " + ent.getInscricao() + " " + ent.getNome() + " " + ent.getNota());
			cont++;
		}
		
		return candidatos;
	}
	
	public static void main(String[] args) {
		
		String valores = "10029259, Wellington Araujo Palmeira, 44.00 / 10078779, Zenilda Nunes da Mata, 50.00 /";
		
		ClassificaCandidatosPorNotaSemRedacao c = new ClassificaCandidatosPorNotaSemRedacao();
		c.classificaCandidatos(valores);
	}

}

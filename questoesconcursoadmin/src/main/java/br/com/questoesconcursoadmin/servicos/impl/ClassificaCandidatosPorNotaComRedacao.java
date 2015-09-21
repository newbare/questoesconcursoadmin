package br.com.questoesconcursoadmin.servicos.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.questoesconcursoadmin.model.Candidato;
import br.com.questoesconcursoadmin.servicos.generic.ClassificaCandidatosPorNotaInterface;

public class ClassificaCandidatosPorNotaComRedacao implements ClassificaCandidatosPorNotaInterface{

	@Override
	public List<Candidato> classificaCandidatos(String valores) {
		return preparaListaCandidatos(valores);
	}
	
	/**
	 * 
	 * Método que ordena as notas dos concursados que estejam disponíveis no formato:
	 * Matrícula, Nome, Nota da Prova, Nota da Redação / Matrícula, Nome, Nota da Prova, Nota da Redação /
	 * 
	 * Exemplo: 123456, Fulado de tal, 50.00, 18.45 / 654321, Ciclano de tal, 48.23, 22.3 / 
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
					index = valores.indexOf(",") - 1;
				}else if(i == 2){
					e.setNota(Double.parseDouble(valores.substring(posicao, index)));
					valores = valores.substring(valores.indexOf(",") + 2, valores.length());
					index = valores.indexOf("/") - 1;
				}else if(i == 3){
					e.setNotaRedacao(Double.parseDouble(valores.substring(posicao, index)));
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
			System.out.println(cont + " " + ent.getInscricao() + " " + ent.getNome() + " " +(ent.getNota() + ent.getNotaRedacao()));
			cont++;
		}
		
		return candidatos;
	}
	
	public static void main(String[] args) {
		
		String valores = "10015455, Alex Sandro Santos Miranda, 56.00, 27.12 / 10036604, Bruno Campos Rodrigues, 56.00, 23.14 / 10066991, Bruno Nagano, 57.00, 25.97 / 10041089, Carlos Henrique Honda, 56.00, 16.79 / 10006039, Daniel Barbosa Cordeiro, 64.00, 28.00 / 10078630, Dennys Nadson Yuzuki Batista, 56.00, 23.13 / 10056964, Eduardo Pereira Borges, 75.00, 34.20 / 10044736, Germano Lucas de Carvalho Costa, 56.00, 30.61 / 10031518, Graciela Prudente da Silva Moronte, 66.00, 27.97 / 10029072, Ivaneide de Sousa Lucio, 62.00, 21.79 / 10042877, Johnson Santana de Carvalho, 64.00, 20.54 / 10008964, Jose Ricardo Guimaraes, 58.00, 29.59 / 10018576, Juliana Rocha Studart, 59.00, 28.81 / 10085636, Leandro Max de Lima Silva, 59.00, 31.92 / 10068734, Leonardo Batista da Silva Rosa, 57.00, 27.38 / 10058456, Leonardo Sant Anna do Valle Dias, 56.00, 26.04 / 10059553, Lucivaldo Queiroz da Costa, 56.00, 20.67 / 10056210, Luiz Henrique do Espirito Santo Andrade, 56.00, 27.97 / 10095515, Marcello Povoa Costa, 58.00, 20.67 / 10058299, Marcelo Vitor Rodrigues, 62.00, 20.89 / 10083347, Pedro Ivo Guimaraes Povoa, 61.00, 0.00 / 10052257, Rajiv Geeverghese, 59.00, 29.60 / 10044581, Ricardo Akl Lasmar de Alvarenga, 60.00, 31.34 / 10039920, Roberto Araujo de Andrade Prata, 56.00, 17.30 / 10041192, Rodrigo Dias Botelho, 58.00, 29.84 / 10068938, Rogerio Vieira Silva, 59.00, 0.00 / 10030049, Vinicius Coelho de Almeida, 65.00, 29.32 /";
		
		ClassificaCandidatosPorNotaComRedacao c = new ClassificaCandidatosPorNotaComRedacao();
		c.classificaCandidatos(valores);
	}

}

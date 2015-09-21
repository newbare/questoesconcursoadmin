package br.com.questoesconcursoadmin.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class HomeMB extends BaseMB{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5247599216612111221L;
	
	private static final String consultarUsuario = "consultarUsuario.jsf";
	private static final String consultarQuestao = "questao/consultarQuestao.jsf";
	private static final String consultarConcurso = "questao/consultarConcurso.jsf";

	public String getConsultarUsuario(){
		return consultarUsuario;
	}

	public String getConsultarQuestao() {
		return consultarQuestao;
	}
	
	public String getConsultarConcurso(){
		return consultarConcurso;
	}
	
}

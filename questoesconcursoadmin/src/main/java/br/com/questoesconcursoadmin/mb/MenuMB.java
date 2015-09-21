package br.com.questoesconcursoadmin.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class MenuMB extends BaseMB{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6717062179980519340L;
	
	private String paginaMenu;
	
	/**
	 * Método que redireciona para a página selecionada no menu.
	 * @return
	 */
	public String redirecionar(){
		removeBean();
		removeBeanDaClasse();
		return this.paginaMenu;
	}

	public String getPaginaMenu() {
		return paginaMenu;
	}

	public void setPaginaMenu(String paginaMenu) {
		this.paginaMenu = paginaMenu;
	}
	
	public String getCadastrarQuestao(){
		return "consultarUsuario.jsf";
	}
	
}

package br.com.questoesconcursoadmin.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.model.Disciplina;
import br.com.questoesconcursoadmin.remote.DisciplinaRemote;

@ManagedBean(name="disciplinaMB")
@SessionScoped
public class DisciplinaMB extends GenericMB<Disciplina> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6218090714724244499L;

	@EJB DisciplinaRemote disciplinaRemote;
	
	private String nomePesquisa;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntity(new Disciplina());
		setEntityRemote(this.disciplinaRemote);
		
	}
	
	public String salvarOuAlterar(){
		
		if(!validaCamposObrigatorios()){
			mostrarMensagemAtencao(getListaMensagens());
			return null;
		}
		
		if(alterar){
			return alterar();
		}else{
			return salvar();
		}
	}
	
	private boolean validaCamposObrigatorios(){
		
		boolean retorno = true;
		getListaMensagens().clear();
		
		if(getEntity().getNome() == null || "".equals(getEntity().getNome().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_NOME")));
			retorno = false;
		}
		
		return retorno;
		
	}
	
	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}
	
}

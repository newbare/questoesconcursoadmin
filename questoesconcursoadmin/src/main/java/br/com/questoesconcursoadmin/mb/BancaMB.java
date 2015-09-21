package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.model.Banca;
import br.com.questoesconcursoadmin.remote.BancaRemote;

@ManagedBean(name="bancaMB")
@SessionScoped
public class BancaMB extends GenericMB<Banca> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2578681129351824063L;
	
	@EJB BancaRemote bancaRemote;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntity(new Banca());
		setEntityRemote(bancaRemote);
		setEntityList(new ArrayList<Banca>());
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

}

package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.model.Area;
import br.com.questoesconcursoadmin.remote.AreaRemote;

@ManagedBean(name="areaMB")
@SessionScoped
public class AreaMB extends GenericMB<Area> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 240652691665311980L;

	@EJB AreaRemote areaRemote;
	
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.areaRemote);
		setEntity(new Area());
		setEntityList(new ArrayList<Area>());
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
		
		if(getEntity().getArea() == null || "".equals(getEntity().getArea().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_AREA")));
			retorno = false;
		}
		
		return retorno;
		
	}

}

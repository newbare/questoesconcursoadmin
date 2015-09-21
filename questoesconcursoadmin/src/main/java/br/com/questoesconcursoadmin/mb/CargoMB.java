package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.model.Cargo;
import br.com.questoesconcursoadmin.remote.CargoRemote;

@ManagedBean(name="cargoMB")
@SessionScoped
public class CargoMB extends GenericMB<Cargo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8635374785412553255L;

	@EJB CargoRemote cargoRemote;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.cargoRemote);
		setEntity(new Cargo());
		setEntityList(new ArrayList<Cargo>());
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
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_CARGO")));
			retorno = false;
		}
		
		return retorno;
		
	}

}

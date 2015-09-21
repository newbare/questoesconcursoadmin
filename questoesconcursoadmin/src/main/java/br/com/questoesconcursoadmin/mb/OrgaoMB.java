package br.com.questoesconcursoadmin.mb;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Orgao;
import br.com.questoesconcursoadmin.remote.OrgaoRemote;

@ManagedBean(name="orgaoMB")
@SessionScoped
public class OrgaoMB extends GenericMB<Orgao> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3322817573224018041L;
	@EJB OrgaoRemote orgaoRemote;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.orgaoRemote);
		setEntity(new Orgao());
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
		
		if(getEntity().getOrgao() == null || "".equals(getEntity().getOrgao().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ORGAO")));
			retorno = false;
		}
		
		if(getEntity().getOrgaoAbreviacao() == null || "".equals(getEntity().getOrgaoAbreviacao().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ABREVIACAO")));
			retorno = false;
		}
		
		return retorno;
		
	}
	
}

package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Especialidade;
import br.com.questoesconcursoadmin.remote.EspecialidadeRemote;

@ManagedBean(name="especialidadeMB")
@SessionScoped
public class EspecialidadeMB extends GenericMB<Especialidade> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6920280559155630528L;

	@EJB EspecialidadeRemote especialidadeRemote;
	
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.especialidadeRemote);
		setEntity(new Especialidade());
		setEntityList(new ArrayList<Especialidade>());
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
		
		if(getEntity().getDescricao() == null || "".equals(getEntity().getDescricao().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ESPECIALIDADE")));
			retorno = false;
		}
		
		return retorno;
		
	}

}

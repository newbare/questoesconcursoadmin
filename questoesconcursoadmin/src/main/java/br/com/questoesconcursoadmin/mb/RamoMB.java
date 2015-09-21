package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.questoesconcursoadmin.constantes.ConstantesNavegacao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Especialidade;
import br.com.questoesconcursoadmin.model.Ramo;
import br.com.questoesconcursoadmin.remote.EspecialidadeRemote;
import br.com.questoesconcursoadmin.remote.RamoRemote;

@ManagedBean(name="ramoMB")
@SessionScoped
public class RamoMB extends GenericMB<Ramo> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 544452836156297078L;
	
	@EJB RamoRemote ramoRemote;
	@EJB EspecialidadeRemote especialidadeRemote;
	
	private List<SelectItem> listaEspecialidade;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.ramoRemote);
		setEntity(new Ramo());
		setEntityList(new ArrayList<Ramo>());
		carregaEspecialidade();
	}
	
	/**
	 * Método que carrega e configura a lista com todas as especialidades disponíveis na base de dados
	 */
	private void carregaEspecialidade(){
		try {
			List<Especialidade> lista = especialidadeRemote.recuperarTodos();
			if(lista != null){
				this.listaEspecialidade = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
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
		
		if(getEntity().getRamo() == null || "".equals(getEntity().getRamo().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_RAMO")));
			retorno = false;
		}
		
		return retorno;
		
	}
	
	public String incluirEspecialidade(){
		removeBeanDaClasse();
		return ConstantesNavegacao.MANTER_ESPECIALIDADE;
	}

	public List<SelectItem> getListaEspecialidade() {
		return listaEspecialidade;
	}

}

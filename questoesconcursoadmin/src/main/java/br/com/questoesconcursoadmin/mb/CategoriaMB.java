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
import br.com.questoesconcursoadmin.model.Categoria;
import br.com.questoesconcursoadmin.model.Disciplina;
import br.com.questoesconcursoadmin.remote.CategoriaRemote;
import br.com.questoesconcursoadmin.remote.DisciplinaRemote;

@ManagedBean(name="categoriaMB")
@SessionScoped
public class CategoriaMB extends GenericMB<Categoria> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4467490805685158203L;
	
	@EJB CategoriaRemote categoriaRemote;
	@EJB DisciplinaRemote disciplinaRemote;
	
	private List<SelectItem> listaDisciplina;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntity(new Categoria());
		setEntityRemote(categoriaRemote);
		setEntityList(new ArrayList<Categoria>());
		carregaDisciplina();
	}
	
	private void carregaDisciplina(){
		try {
			List<Disciplina> lista = disciplinaRemote.recuperarTodos();
			if(lista != null){
				this.listaDisciplina = generateSelectItens(lista);
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
		
		if(getEntity().getNome() == null || "".equals(getEntity().getNome().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_NOME")));
			retorno = false;
		}
		
		if(getEntity().getDisciplina() == null || getEntity().getDisciplina().getId() < 1){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_DISCIPLINA")));
			retorno = false;
		}
		
		return retorno;
		
	}
	
	public String incluirDisciplina(){
		removeBeanDaClasse();
		return ConstantesNavegacao.MANTER_DISCIPLINA;
	}

	public List<SelectItem> getListaDisciplina() {
		return listaDisciplina;
	}

}

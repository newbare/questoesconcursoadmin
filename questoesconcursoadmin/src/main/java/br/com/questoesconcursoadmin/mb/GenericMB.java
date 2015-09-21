package br.com.questoesconcursoadmin.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.questoesconcursoadmin.constantes.ConstantesNavegacao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.BaseDomain;
import br.com.questoesconcursoadmin.remote.GenericRemote;

public abstract class GenericMB<T extends BaseDomain> extends BaseMB implements Serializable {

	private static final long serialVersionUID = -7181917363933510164L;

	private List<String> listaMensagens = new ArrayList<String>();
	private FacesContext facesContext;
	private Boolean messages = false;
	private String messageTitulo;
	private Boolean popUp;
	private Boolean tabelaPesquisa = false;
	Boolean alterar = false;
	private Boolean renderizaTabelaPesquia = false;
	private String retornoRedirecionar;
	private T entity;
	private List<T> entityList;
	private GenericRemote entityRemote;
	
	public String novo(){
		removeBeanDaClasse();
		this.alterar = false;
		return ConstantesNavegacao.NOVO;
	}
	
	public String voltar(){
		this.alterar = false;
		if(this.retornoRedirecionar != null && !"".equals(this.retornoRedirecionar.trim())){
			return this.retornoRedirecionar;
		}
		return ConstantesNavegacao.VOLTAR;
	}
	
	public String editar(){
		this.alterar = true;
		return ConstantesNavegacao.EDITAR;
	}
	
	public void mostrarMensagemErro(List<String> listaErros) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		for (int i = 0; i < listaErros.size(); i++) {
			msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary("ERRO!");
			msg.setDetail(listaErros.get(i));
			context.addMessage(null, msg);
		}
		setMessages(true);
	}
	
	public void mostrarMensagemErro(String erro) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		msg.setSummary("ERRO!");
		msg.setDetail(erro);
		context.addMessage(null, msg);
		setMessages(true);
	}
	
	public void mostrarMensagemAtencao(List<String> listaErros) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		for (int i = 0; i < listaErros.size(); i++) {
			msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_WARN);
			msg.setSummary("Atenção!");
			msg.setDetail(listaErros.get(i));
			context.addMessage(null, msg);
		}
		setMessages(true);
	}
	
	public void mostrarMensagemInformacao(List<String> listaErros) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = null;
		for (int i = 0; i < listaErros.size(); i++) {
			msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			msg.setSummary("Informação!");
			msg.setDetail(listaErros.get(i));
			context.addMessage(null, msg);
		}
		setMessages(true);
	}
	
	public void mostrarMensagemInformacao(String info) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("INFO!");
		msg.setDetail(info);
		context.addMessage(null, msg);
		setMessages(true);
	}

	public void fecharPopUpErro(ActionEvent event) {
		setPopUp(false);
	}

	public void fecharDlgPesquisar(){
		setTabelaPesquisa(false);
	}

	public boolean validarEmail(String email) {
		Pattern p = Pattern
				.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
		Matcher m = p.matcher(email);
		if (m.find()) {
			return true;
		} else {
			getListaMensagens().clear();
			getListaMensagens().add("O email informado é inválido!");
			return false;
		}
	}

	public boolean isCPF(String CPF) { 
		CPF = CPF.replace(".", "");
		CPF = CPF.replace("-", "");
		CPF.trim();
		if (CPF.equals("00000000000") || CPF.equals("11111111111")
				|| CPF.equals("22222222222") || CPF.equals("33333333333")
				|| CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777")
				|| CPF.equals("88888888888") || CPF.equals("99999999999")
				|| (CPF.length() != 11)){
			getListaMensagens().clear();
			getListaMensagens().add("O CPF informado é inválido!");
			return (false);
		}
		char dig10, dig11;
		int sm, i, r, num, peso;
	
		try { 
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48);
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}
			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
				return (true);
			else{
				getListaMensagens().clear();
				getListaMensagens().add("O CPF informado é inválido!");
				return (false);
			}
		} catch (InputMismatchException erro) {
			getListaMensagens().clear();
			getListaMensagens().add("O CPF informado é inválido!");
			return (false);
		}
	}
	
	public String excluir(){
		
		try {
			entityRemote.deletar(this.entity);
			if(entityList != null){
				for(T t : entityList){
					if(t.equals(entity)){
						entityList.remove(t);
						break;
					}
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_DELETAR"));
			return null;
		} catch (EJBTransactionRolledbackException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_DELETAR_CONSTRAINT"));
			return null;
		}
		
		mostrarMensagemInformacao(getMessageBundle("Mensagem.MSG_SUCESSO_REGISTRO_EXCLUIDO"));
		return null;
	}
	
	public String pesquisar(){
		
		try {
			setEntityList(entityRemote.recuperarPorEntidade(getEntity()));
			if(getEntityList() != null && getEntityList().size() > 0){
				setRenderizaTabelaPesquia(true);
			}else{
				mostrarMensagemInformacao(getMessageBundle("Mensagem.MSG_INFORMATIVA_NENHUM_REGISTRO_ENCONTRADO"));
				setRenderizaTabelaPesquia(false);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_PESQUISA"));
			return null;
		}
		
		return null;
	}
	
	public String salvar(){
		long t1 = System.nanoTime();
		try {
			entityRemote.inserir(this.entity);
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_SALVAR"));
			return null;
		}
		
		if(getRetornoRedirecionar() != null && !"".equals(getRetornoRedirecionar().trim())){
			removeBeanDaClasse();
			removeBean();
			return getRetornoRedirecionar();
		}
		
		if(getRetornoRedirecionar() != null && !"".equals(getRetornoRedirecionar().trim())){
			removeBeanDaClasse();
			removeBean();
			return getRetornoRedirecionar();
		}else{
			removeBeanDaClasse();
			long t2 = System.nanoTime();
			System.out.println("Tempo de execução: " + ((t2 - t1) * 1e-9) + " segundos");
			return ConstantesNavegacao.SALVAR;
		}
	}
	
	public String salvar(List<T> lista){
		try {
			entityRemote.inserir(lista);
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_SALVAR"));
			return null;
		}
		
		if(getRetornoRedirecionar() != null && !"".equals(getRetornoRedirecionar().trim())){
			removeBeanDaClasse();
			removeBean();
			return getRetornoRedirecionar();
		}
		removeBeanDaClasse();
		return ConstantesNavegacao.SALVAR;
	}	
	
	public String alterar(){
		
		try {
			entityRemote.alterar(getEntity());
			removeBeanDaClasse();
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_SALVAR"));
			return null;
		}
		
		this.alterar = false;
		return ConstantesNavegacao.ALTERAR;
	}
	
	public String alterar(List<T> lista){
		
		try {
			entityRemote.alterar(lista);
			removeBeanDaClasse();
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_SALVAR"));
			return null;
		}
		
		this.alterar = false;
		return ConstantesNavegacao.ALTERAR;
	}
	
	public List<String> getListaMensagens() {
		return listaMensagens;
	}

	public void setListaMensagens(List<String> listaMensagens) {
		this.listaMensagens = listaMensagens;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public Boolean getMessages() {
		return messages;
	}

	public void setMessages(Boolean messages) {
		this.messages = messages;
	}

	public String getMessageTitulo() {
		return messageTitulo;
	}

	public void setMessageTitulo(String messageTitulo) {
		this.messageTitulo = messageTitulo;
	}

	public Boolean getPopUp() {
		return popUp;
	}

	public void setPopUp(Boolean popUp) {
		this.popUp = popUp;
	}

	public Boolean getTabelaPesquisa() {
		return tabelaPesquisa;
	}

	public void setTabelaPesquisa(Boolean tabelaPesquisa) {
		this.tabelaPesquisa = tabelaPesquisa;
	}

	public Boolean getRenderizaTabelaPesquia() {
		return renderizaTabelaPesquia;
	}

	public void setRenderizaTabelaPesquia(Boolean renderizaTabelaPesquia) {
		this.renderizaTabelaPesquia = renderizaTabelaPesquia;
	}

	public String getRetornoRedirecionar() {
		return retornoRedirecionar;
	}

	public void setRetornoRedirecionar(String retornoRedirecionar) {
		this.retornoRedirecionar = retornoRedirecionar;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public GenericRemote getEntityRemote() {
		return entityRemote;
	}

	public void setEntityRemote(GenericRemote entityRemote) {
		this.entityRemote = entityRemote;
	}

	public List<T> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<T> entityList) {
		this.entityList = entityList;
	}

}

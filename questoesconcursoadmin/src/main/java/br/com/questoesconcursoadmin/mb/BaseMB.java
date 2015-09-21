package br.com.questoesconcursoadmin.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.questoesconcursoadmin.util.JSFUtil;


public class BaseMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String MB_ATUAL = "atual";
	
	@PostConstruct
	public void init() {
		gravarBeanNaSessao(MB_ATUAL);
	}
	
	protected void gravarBeanNaSessao(String variavelSessao){
		ManagedBean man = this.getClass().getAnnotation(ManagedBean.class);
		getHttpSession().setAttribute(variavelSessao, man.name());
	}
	
	protected HttpSession getHttpSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession)context.getExternalContext().getSession(true);
		return session;
	}
	
	protected boolean hasHole(String hole) {
		SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext){
            Authentication authentication = context.getAuthentication();
            return authentication.getAuthorities().contains(new GrantedAuthorityImpl(hole));
        }
        return false;    
	}
	
	protected void removeBean() {
		if(getHttpSession().getAttribute(MB_ATUAL) != null) {
			getHttpSession().removeAttribute(getHttpSession().getAttribute(MB_ATUAL).toString());
			getHttpSession().removeAttribute(MB_ATUAL);
		}
	}
	
	protected void removeBeanDaClasse(){
		ManagedBean man = this.getClass().getAnnotation(ManagedBean.class);
		if(getHttpSession().getAttribute(man.name()) != null) {
			getHttpSession().removeAttribute(getHttpSession().getAttribute(man.name()).toString());
			getHttpSession().removeAttribute(man.name());
		}
	}
	
	protected String getMessageBundle(String key) {
		String[] strs = key.split("\\.");
		String text = "";
		if(strs.length > 1) {
			text = JSFUtil.getMessageResourceBundle( strs[0], strs[1] );
		} else {
			text = JSFUtil.getMessageResourceBundle( key );
		}
		return text;
	}
	
	protected ArrayList<SelectItem> generateSelectItens(List<?> lista) {
		ArrayList<SelectItem> itens = new ArrayList<SelectItem>();
		SelectItem item = null;
		for(Object o : lista) {
			item = new SelectItem(o);
			itens.add(item);
		}
		return itens;
	}

}

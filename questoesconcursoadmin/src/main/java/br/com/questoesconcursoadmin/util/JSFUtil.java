package br.com.questoesconcursoadmin.util;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import br.com.questoesconcursoadmin.constantes.ConstantesSistema;

public class JSFUtil {
	
	private static final Logger log = Logger.getLogger( JSFUtil.class );
	
	public static String getMessageResourceBundle( String key ){
		
		FacesContext context = FacesContext.getCurrentInstance();
	    String text = null;   
	    try {   
	        ResourceBundle bundle = ResourceBundle.getBundle(ConstantesSistema.LOCALE_DEFAULT, context.getViewRoot().getLocale());   
	        text = bundle.getString(key);   
	    } catch (Exception e) {
	    	log.error( e );
	    } 
	    
	    return text;
	}
	
	
	public static String getMessageResourceBundle( String resource, String key ){
		
		FacesContext context = FacesContext.getCurrentInstance();
	    String text = null;   
	    try {   
	        ResourceBundle bundle = ResourceBundle.getBundle(ConstantesSistema.LOCALE_PATH.concat(".").concat(resource).concat("_pt")
	        		, context.getViewRoot().getLocale());   
	        text = bundle.getString(key);   
	    } catch (Exception e) {
	    	log.error( e );
	    }
	    
	    return text;
	}

}

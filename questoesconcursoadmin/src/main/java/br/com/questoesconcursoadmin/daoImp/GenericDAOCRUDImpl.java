package br.com.questoesconcursoadmin.daoImp;

import java.lang.reflect.Field;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import br.com.questoesconcursoadmin.dao.GenericDAOCRUD;
import br.com.questoesconcursoadmin.model.BaseDomain;

/**
 * Session Bean implementation class GenericDAOCRUDImpl
 */
@Stateless
@LocalBean
public class GenericDAOCRUDImpl extends GenericDAOJPAImpl<BaseDomain, Integer> implements GenericDAOCRUD {

    /**
     * Default constructor.  
     */
    public GenericDAOCRUDImpl() {
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setEntityClass(Class clazz) {
		super.setEntityClass(clazz);
	}


	@SuppressWarnings("unused")
	private Object getValue(BaseDomain entity, String property) throws PersistenceException {
		try {
			Field field  = getEntityClass().getDeclaredField(property);
			field.setAccessible(true);
			Object o = field.get(entity);
			return o;
		} catch (SecurityException e) {
			throw new PersistenceException();
		} catch (NoSuchFieldException e) {
			throw new PersistenceException();
		} catch (IllegalAccessException e) {
			throw new PersistenceException();
		}
	}
    
    

}

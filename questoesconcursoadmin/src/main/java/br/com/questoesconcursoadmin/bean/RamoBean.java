package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.RamoDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Ramo;
import br.com.questoesconcursoadmin.remote.RamoRemote;

@Stateless
@LocalBean
public class RamoBean extends GenericBean<Ramo, Integer> implements RamoRemote{
	
	@EJB RamoDao ramoDAO;
	
	@Override
	public List<Ramo> recuperarPorEntidade(Ramo entity)
			throws BusinessException {
		return ramoDAO.findByEntity(entity);
	}
	
}

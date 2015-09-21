package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.AreaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Area;
import br.com.questoesconcursoadmin.remote.AreaRemote;

@Stateless
@LocalBean
public class AreaBean extends GenericBean<Area, Integer> implements AreaRemote{
	
	@EJB AreaDao areaDAO;
	
	@Override
	public List<Area> recuperarPorEntidade(Area entity)
			throws BusinessException {
		return areaDAO.findByEntity(entity);
	}
	
}

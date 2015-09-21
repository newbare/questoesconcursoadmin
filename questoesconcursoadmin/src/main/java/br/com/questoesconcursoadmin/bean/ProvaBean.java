package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.ProvaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Prova;
import br.com.questoesconcursoadmin.remote.ProvaRemote;

@Stateless
@LocalBean
public class ProvaBean extends GenericBean<Prova, Integer> implements ProvaRemote{
	
	@EJB ProvaDao provaDAO;
	
	@Override
	public Prova findyByCCAId(Integer idCCA) {
		return provaDAO.findyByCCAId(idCCA);
	}
	
	@Override
	public List<Prova> recuperarPorEntidade(Prova entity)
			throws BusinessException {
		return provaDAO.findByEntity(entity);
	}
}

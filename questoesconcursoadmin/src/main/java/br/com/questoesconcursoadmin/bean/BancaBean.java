package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.BancaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Banca;
import br.com.questoesconcursoadmin.remote.BancaRemote;

@Stateless
@LocalBean
public class BancaBean extends GenericBean<Banca, Integer> implements BancaRemote{
	
	@EJB BancaDao bancaDAO;
	
	@Override
	public List<Banca> recuperarPorEntidade(Banca entity)
			throws BusinessException {
		return bancaDAO.findByEntity(entity);
	}
	
}

package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.AlternativaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Alternativa;
import br.com.questoesconcursoadmin.remote.AlternativaRemote;

@Stateless
@LocalBean
public class AlternativaBean extends GenericBean<Alternativa, Long> implements AlternativaRemote{
	
	@EJB AlternativaDao alternativaDAO;
	
	@Override
	public List<Alternativa> recuperarPorEntidade(Alternativa entity)
			throws BusinessException {
		return alternativaDAO.findByEntity(entity);
	}

	@Override
	public Alternativa findOneByEntity(Alternativa entity)
			throws BusinessException {
		return alternativaDAO.findOneByEntity(entity);
	}
	
}

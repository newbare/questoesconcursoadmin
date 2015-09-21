package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.OrgaoDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Orgao;
import br.com.questoesconcursoadmin.remote.OrgaoRemote;

@Stateless
@LocalBean
public class OrgaoBean extends GenericBean<Orgao, Long> implements OrgaoRemote{
	
	@EJB OrgaoDao orgaoDAO;;
	
	@Override
	public List<Orgao> recuperarPorEntidade(Orgao entity)
			throws BusinessException {
		return orgaoDAO.findByEntity(entity);
	}
	
}

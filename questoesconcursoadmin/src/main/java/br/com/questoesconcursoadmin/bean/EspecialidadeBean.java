package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.EspecialidadeDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Especialidade;
import br.com.questoesconcursoadmin.remote.EspecialidadeRemote;

@Stateless
@LocalBean
public class EspecialidadeBean extends GenericBean<Especialidade, Long> implements EspecialidadeRemote{
	
	@EJB EspecialidadeDao especialidadeDAO;
	
	@Override
	public List<Especialidade> recuperarPorEntidade(Especialidade entity)
			throws BusinessException {
		return especialidadeDAO.findByEntity(entity);
	}
	
}

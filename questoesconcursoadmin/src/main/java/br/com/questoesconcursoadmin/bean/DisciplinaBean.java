package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.DisciplinaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Disciplina;
import br.com.questoesconcursoadmin.remote.DisciplinaRemote;

@Stateless
@LocalBean
public class DisciplinaBean extends GenericBean<Disciplina, Integer> implements DisciplinaRemote{
	
	@EJB DisciplinaDao disciplinaDAO;
	
	@Override
	public List<Disciplina> recuperarPorEntidade(Disciplina entity)
			throws BusinessException {
		return disciplinaDAO.findByEntity(entity);
	}
	
}

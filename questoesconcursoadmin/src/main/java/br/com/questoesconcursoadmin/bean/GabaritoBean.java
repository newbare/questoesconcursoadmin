package br.com.questoesconcursoadmin.bean;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.GabaritoDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Gabarito;
import br.com.questoesconcursoadmin.remote.GabaritoRemote;

@Stateless
@LocalBean
public class GabaritoBean extends GenericBean<Gabarito, Long> implements GabaritoRemote{
	
	@EJB GabaritoDao gabaritoDAO;

	@Override
	public Gabarito findGabaritoByQuestion(Long questao)
			throws BusinessException {
		return gabaritoDAO.findGabaritoByQuestion(questao);
	}
	
}

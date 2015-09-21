package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.CargoConcursoAreaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;
import br.com.questoesconcursoadmin.remote.CargoConcursoAreaRemote;

@Stateless
@LocalBean
public class CargoConcursoAreaBean extends GenericBean<CargoConcursoArea, Long> implements CargoConcursoAreaRemote{

	@EJB CargoConcursoAreaDao ccaDAO;
	
	@Override
	public List<CargoConcursoArea> findByCodigoConcurso(Long codigoConcurso)
			throws BusinessException {
		return ccaDAO.findByCodigoConcurso(codigoConcurso);
	}

}

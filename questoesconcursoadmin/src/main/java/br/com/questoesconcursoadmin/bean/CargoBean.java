package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.CargoDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Cargo;
import br.com.questoesconcursoadmin.remote.CargoRemote;

@Stateless
@LocalBean
public class CargoBean extends GenericBean<Cargo, Integer> implements CargoRemote{
	
	@EJB CargoDao cargoDAO;
	
	@Override
	public List<Cargo> recuperarPorEntidade(Cargo entity)
			throws BusinessException {
		return cargoDAO.findByEntity(entity);
	}
	
}

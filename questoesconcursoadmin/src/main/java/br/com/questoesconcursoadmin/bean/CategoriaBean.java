package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.CategoriaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Categoria;
import br.com.questoesconcursoadmin.remote.CategoriaRemote;

@Stateless
@LocalBean
public class CategoriaBean extends GenericBean<Categoria, Integer> implements CategoriaRemote{
	
	@EJB CategoriaDao categoriaDAO;
	
	@Override
	public List<Categoria> recuperarPorEntidade(Categoria entity)
			throws BusinessException {
		return categoriaDAO.findByEntity(entity);
	}
	
}

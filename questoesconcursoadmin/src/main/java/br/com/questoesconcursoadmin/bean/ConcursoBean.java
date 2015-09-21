package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.CargoConcursoAreaDao;
import br.com.questoesconcursoadmin.dao.ConcursoDao;
import br.com.questoesconcursoadmin.dao.ProvaDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;
import br.com.questoesconcursoadmin.model.Concurso;
import br.com.questoesconcursoadmin.model.Prova;
import br.com.questoesconcursoadmin.remote.ConcursoRemote;

@Stateless
@LocalBean
public class ConcursoBean extends GenericBean<Concurso, Long> implements ConcursoRemote{
	
	@EJB ConcursoDao concursoDAO;
	@EJB CargoConcursoAreaDao cargoConcursoAreaDao;
	@EJB ProvaDao provaDAO;
	
	@Override
	public List<Concurso> recuperarPorEntidade(Concurso entity)
			throws BusinessException {
		return concursoDAO.findByEntity(entity);
	}
	
	@Override
	public Concurso inserir(Concurso entity) {
		
		entity.setConcluido(false);
		entity = super.inserir(entity);
		
		insereCargoConcursoArea(entity);
		
		return entity;
	}

	private void insereCargoConcursoArea(Concurso entity) {
		for(CargoConcursoArea cca : entity.getListaCargoConcursoArea()){
			cca.setIdConcurso(entity.getId());
			cargoConcursoAreaDao.insert(cca);
			inserirProva(cca);
		}
	}
	
	private void inserirProva(CargoConcursoArea cca){
		Prova prova = new Prova();
		prova.setCargoConcursoArea(cca.getId());
		prova.setConcluida(false);
		provaDAO.insert(prova);
	}
	
	@Override
	public void alterar(Concurso entity) {
		cargoConcursoAreaDao.deleteByConcurso(entity.getId());
		super.alterar(entity);
		insereCargoConcursoArea(entity);
	}
	
	@Override
	public void deletar(Concurso entity) {
		cargoConcursoAreaDao.deleteByConcurso(entity.getId());
		super.deletar(entity);
	}
	
}

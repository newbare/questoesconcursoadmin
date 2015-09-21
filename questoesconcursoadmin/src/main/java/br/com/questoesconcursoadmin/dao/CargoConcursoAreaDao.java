package br.com.questoesconcursoadmin.dao;

import java.util.List;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.model.CargoConcursoArea;

@Local
public interface CargoConcursoAreaDao extends GenericDAO<CargoConcursoArea, Long>{
	
	public List<CargoConcursoArea> findByCodigoConcurso(Long codigoConcurso);
	
	public void deleteByConcurso(Long codigoConcurso);
	
}

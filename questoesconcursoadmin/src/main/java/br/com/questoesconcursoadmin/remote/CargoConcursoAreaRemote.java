package br.com.questoesconcursoadmin.remote;

import java.util.List;

import javax.ejb.Local;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;

@Local
public interface CargoConcursoAreaRemote extends GenericRemote<CargoConcursoArea, Long>{
	
	public List<CargoConcursoArea> findByCodigoConcurso(Long codigoConcurso) throws BusinessException;
	
}

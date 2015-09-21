package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import br.com.questoesconcursoadmin.dao.CargoConcursoAreaDao;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;

@Stateless
@LocalBean
public class CargoConcursoAreaDaoImp extends GenericDAOJPAImpl<CargoConcursoArea, Long> implements CargoConcursoAreaDao{

	@Override
	public List<CargoConcursoArea> findByCodigoConcurso(Long codigoConcurso) {
		Query query = em.createQuery("select cca from CargoConcursoArea cca where cca.idConcurso = :idConcurso");
		query.setParameter("idConcurso", codigoConcurso);
		return (List<CargoConcursoArea>) query.getResultList();
	}

	@Override
	public void deleteByConcurso(Long codigoConcurso) {
		Query query = em.createQuery("delete from CargoConcursoArea cca where cca.idConcurso = :con_id");
		query.setParameter("con_id", codigoConcurso);
		query.executeUpdate();
	}

}

package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.questoesconcursoadmin.dao.QuestaoProvaDao;
import br.com.questoesconcursoadmin.model.QuestaoProva;

@Stateless
@LocalBean
public class QuestaoProvaDaoImp extends GenericDAOJPAImpl<QuestaoProva, Long> implements QuestaoProvaDao{
	
	@Override
	public void deleteByCodigoQuestao(Long codigoQuestao)
			throws PersistenceException {
		Query query = em.createQuery("delete from QuestaoProva qp where qo.idQuestao = :idQuestao");
		query.setParameter("idQuestao", codigoQuestao);
		query.executeUpdate();
	}
	
	@Override
	public void deleteByCodigoProva(Long codigoProva)
			throws PersistenceException {
		Query query = em.createQuery("delete from QuestaoProva qp where qp.idProva = :idProva");
		query.setParameter("idProva", codigoProva);
		query.executeUpdate();
	}

	@Override
	public List<QuestaoProva> findByCodigoQuestao(Long codigoQuestao)
			throws PersistenceException {
		Query query = em.createQuery("select qp from QuestaoProva qp where qp.idQuestao = :idQuestao");
		query.setParameter("idQuestao", codigoQuestao);
		return query.getResultList();
	}
	
	@Override
	public List<QuestaoProva> findByCodigoProva(Long codigoProva)
			throws PersistenceException {
		Query query = em.createQuery("select qp from QuestaoProva qp where qp.idProva = :idProva");
		query.setParameter("idProva", codigoProva);
		return query.getResultList();
	}

}

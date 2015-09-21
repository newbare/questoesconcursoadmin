package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.QuestaoComentarioDao;
import br.com.questoesconcursoadmin.model.QuestaoComentario;

@Stateless
@LocalBean
public class QuestaoComentarioDaoImp extends GenericDAOJPAImpl<QuestaoComentario, Integer> implements QuestaoComentarioDao{

}

package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.ComentarioDao;
import br.com.questoesconcursoadmin.model.Comentario;

@Stateless
@LocalBean
public class ComentarioDaoImp extends GenericDAOJPAImpl<Comentario, Integer> implements ComentarioDao{

}

package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.FormaInclusaoDao;
import br.com.questoesconcursoadmin.model.FormaInclusao;

@Stateless
@LocalBean
public class FormaInclusaoDaoImp extends GenericDAOJPAImpl<FormaInclusao, Integer> implements FormaInclusaoDao{

}

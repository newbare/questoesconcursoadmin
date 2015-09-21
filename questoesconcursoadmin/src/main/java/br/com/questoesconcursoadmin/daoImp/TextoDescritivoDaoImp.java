package br.com.questoesconcursoadmin.daoImp;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.TextoDescritivoDao;
import br.com.questoesconcursoadmin.model.TextoDescritivo;

@Stateless
@LocalBean
public class TextoDescritivoDaoImp extends GenericDAOJPAImpl<TextoDescritivo, Integer> implements TextoDescritivoDao{

}

package br.com.questoesconcursoadmin.daoImp;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.questoesconcursoadmin.dao.PessoaDao;
import br.com.questoesconcursoadmin.model.Pessoa;

@Stateless
@LocalBean
public class PessoaDaoImp extends GenericDAOJPAImpl<Pessoa, Integer> implements PessoaDao{

	@Override
	public List<Pessoa> findByEntity(Pessoa pessoa) throws PersistenceException {
		
		Session session = (Session) em.getDelegate();
		Criteria c = session.createCriteria(Pessoa.class, "p");
		c.createAlias("p.usuario", "u");
		if(pessoa.getNome() != null){
			c.add(Restrictions.ilike("p.nome", pessoa.getNome(), MatchMode.ANYWHERE));
		}
		
		if(pessoa.getCpf() != null && pessoa.getCpf().trim().length() > 0){
			c.add(Restrictions.eq("p.cpf", pessoa.getCpf()));
		}
		
		if(pessoa.getUsuario() != null && pessoa.getUsuario().getEmail() != null){
			c.add(Restrictions.ilike("u.email", pessoa.getUsuario().getEmail(), MatchMode.ANYWHERE));
		}
				
		List<Pessoa> lista = (List<Pessoa>) c.list();
		return lista;
	}
 
}

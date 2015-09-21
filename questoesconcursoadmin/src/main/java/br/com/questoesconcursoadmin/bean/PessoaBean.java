package br.com.questoesconcursoadmin.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.questoesconcursoadmin.dao.PerfilDao;
import br.com.questoesconcursoadmin.dao.PessoaDao;
import br.com.questoesconcursoadmin.dao.UsuarioDao;
import br.com.questoesconcursoadmin.dao.UsuarioPerfilDao;
import br.com.questoesconcursoadmin.model.Pessoa;
import br.com.questoesconcursoadmin.model.UsuarioPerfil;
import br.com.questoesconcursoadmin.remote.PessoaRemote;

@Stateless
@LocalBean
public class PessoaBean extends GenericBean<Pessoa, Integer> implements PessoaRemote{

	@EJB PessoaDao pessoaDAO;
	@EJB UsuarioDao usuarioDAO;
	@EJB UsuarioPerfilDao usuarioPerfilDao;
	@EJB PerfilDao perfilDao;
	
	
	@Override
	public Pessoa inserir(Pessoa entity) {
		
		List<String> perfis = entity.getUsuario().getPerfis();
		entity.setUsuario(usuarioDAO.merge(entity.getUsuario()));
		UsuarioPerfil usuarioPerfil;
		for(int i = 0; i < perfis.size(); i++){
			usuarioPerfil = new UsuarioPerfil();
			usuarioPerfil.setIdPerfil(perfilDao.findByAuthority(perfis.get(i)).getId());
			usuarioPerfil.setIdUsuario(entity.getUsuario().getId());
			usuarioPerfilDao.merge(usuarioPerfil);
		}
		entity = super.inserir(entity);
		
		return entity;
	}

	@Override
	public void deletar(Pessoa entity) {
		super.deletar(entity);
		
		if(entity.getUsuario() != null && entity.getUsuario().getId() != null && entity.getUsuario().getId() > 0){
			
			UsuarioPerfil usuarioPerfil = new UsuarioPerfil();
			usuarioPerfil.setIdUsuario(entity.getUsuario().getId());
			usuarioPerfilDao.deleteByCodigoUsuario(entity.getUsuario().getId());
			usuarioDAO.delete(entity.getUsuario());
		}
	}

	@Override
	public List<Pessoa> findByEntity(Pessoa pessoa) {
		return pessoaDAO.findByEntity(pessoa);
	}
	
	@Override
	public void alterar(Pessoa entity) {
		
		List<String> perfis = entity.getUsuario().getPerfis();
		entity.setUsuario(usuarioDAO.update(entity.getUsuario()));
		UsuarioPerfil usuarioPerfil;
		usuarioPerfilDao.deleteByCodigoUsuario(entity.getUsuario().getId());
		for(int i = 0; i < perfis.size(); i++){
			usuarioPerfil = new UsuarioPerfil();
			usuarioPerfil.setIdPerfil(perfilDao.findByAuthority(perfis.get(i)).getId());
			usuarioPerfil.setIdUsuario(entity.getUsuario().getId());
			usuarioPerfilDao.merge(usuarioPerfil);
		}
		
		super.alterar(entity);
	}

}

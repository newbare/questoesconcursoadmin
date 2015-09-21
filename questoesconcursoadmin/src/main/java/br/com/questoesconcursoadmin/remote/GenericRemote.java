package br.com.questoesconcursoadmin.remote;
import java.util.List;

import javax.ejb.Remote;

import br.com.questoesconcursoadmin.exception.BusinessException;


@Remote
public interface GenericRemote<T, ID> {
	
	public T recuperar(ID id) throws BusinessException;
	
	public List<T> recuperarTodos() throws BusinessException;
		
	public T inserir(T entity) throws BusinessException;
	
	public void alterar(T entity) throws BusinessException;
	
	public void deletar(T entity) throws BusinessException;
	
	public List<T> recuperarPorEntidade(T entity) throws BusinessException;
	
	public List<T> inserir(List<T> lista) throws BusinessException;
	
	public void alterar(List<T> lista) throws BusinessException;
	
}

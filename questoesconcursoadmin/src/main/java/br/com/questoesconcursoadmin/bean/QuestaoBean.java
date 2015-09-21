package br.com.questoesconcursoadmin.bean;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import br.com.questoesconcursoadmin.dao.AlternativaDao;
import br.com.questoesconcursoadmin.dao.GabaritoDao;
import br.com.questoesconcursoadmin.dao.QuestaoDao;
import br.com.questoesconcursoadmin.dao.QuestaoProvaDao;
import br.com.questoesconcursoadmin.dao.TextoDescritivoDao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Alternativa;
import br.com.questoesconcursoadmin.model.Gabarito;
import br.com.questoesconcursoadmin.model.Prova;
import br.com.questoesconcursoadmin.model.Questao;
import br.com.questoesconcursoadmin.model.QuestaoProva;
import br.com.questoesconcursoadmin.model.TextoDescritivo;
import br.com.questoesconcursoadmin.model.constante.TipoQuestao;
import br.com.questoesconcursoadmin.remote.QuestaoRemote;

@Stateful
@LocalBean
public class QuestaoBean extends GenericBean<Questao, Long> implements QuestaoRemote{
	
	@EJB QuestaoDao questaoDAO;
	@EJB TextoDescritivoDao textoDescritivoDAO;
	@EJB GabaritoDao gabaritoDAO;
	@EJB AlternativaDao alternativaDAO;
	@EJB QuestaoProvaDao questaoProvaDAO;
	
	private TextoDescritivo textoDescritivo;
	Prova[] provas = null;
	@Override
	public Questao inserir(Questao entity) {
		
		if((provas == null || provas.length <= 0) && entity.getProvas() != null && entity.getProvas().length > 0){
			provas = entity.getProvas();
		}
		
		if(textoDescritivo == null 
				|| textoDescritivo.getTexto() == null 
				|| "".equals(textoDescritivo.getTexto()) 
				|| !textoDescritivo.getTexto().equals(entity.getTextoDescritivo().getTexto())){
			textoDescritivo = textoDescritivoDAO.merge(entity.getTextoDescritivo());
		}
		
		entity.setTextoDescritivo(textoDescritivo);
		entity.setDataCadastro(new Date());
		entity = super.inserir(entity);
		
		if(entity.getTipoQuestao().equals(TipoQuestao.MULTIPLA_ESCOLHA.getCodigo())){
			for(Alternativa a : entity.getListaAlternativa()){
				a.setQuestao(entity);
				if(a.getAlternativa() != null && a.getAlternativa().equals(entity.getAlternativaGabarito().getAlternativa())){
					entity.setAlternativaGabarito(alternativaDAO.merge(a));
				} else{
					alternativaDAO.insert(a);
				}
			}
		}
		
		if(entity.getAlternativaGabarito().getId() != null && entity.getAlternativaGabarito().getId() > 0){
			gabaritoDAO.insert(getGabaritoConfigurado(entity));
		}
		
		if(provas != null && provas.length > 0){
			associaProvaQuestao(provas, entity);
		}
		
		return entity;
	}
	
	/**
	 * Caso a prova esteja sendo cadastrada junto as questão é feita a associação das provas as questões.
	 * @param entity
	 */
	private void associaProvaQuestao(Prova[] provas, Questao entity){
		
		QuestaoProva qp;
		for(Prova p : provas){
			qp = new QuestaoProva();
			qp.setIdProva(p.getId());
			qp.setIdQuestao(entity.getId());
			questaoProvaDAO.insert(qp);
		}
		
	}
	
	@Override
	public List<Questao> inserir(List<Questao> lista) throws BusinessException {
		for(Questao q : lista){
			inserir(q);
		}
		return lista;
	}
	
	private Gabarito getGabaritoConfigurado(Questao questao){
		Gabarito gabarito = new Gabarito();
		gabarito.setIdQuestao(questao.getId());
		gabarito.setIdAlternativa(questao.getAlternativaGabarito().getId());
		
		return gabarito;
	}
	
	@Override
	public List<Questao> recuperarPorEntidade(Questao entity)
			throws BusinessException {
		return questaoDAO.findByEntity(entity);
	}
	
	@Override
	public void alterar(List<Questao> lista) {
		for(Questao q : lista){
			alterar(q);
		}
	}
	
	@Override
	public void alterar(Questao entity) {
		
		textoDescritivoDAO.merge(entity.getTextoDescritivo());
		
		entity.setDataAlteracao(new Date());
		super.alterar(entity);
		
		gabaritoDAO.deleteByQuestion(entity.getId());
		
		if(entity.getTipoQuestao().equals(TipoQuestao.MULTIPLA_ESCOLHA.getCodigo())){
			for(Alternativa a : entity.getListaAlternativa()){
				if(a.getQuestao() == null || a.getQuestao().getId() == null){
					a.setQuestao(entity);
				}
				
				if(a.getAlternativa() != null && a.getAlternativa().equals(entity.getAlternativaGabarito().getAlternativa())){
					entity.setAlternativaGabarito(alternativaDAO.merge(a));
				} else{
					if(a.getId() != null && a.getId() > 0){
						alternativaDAO.update(a);
					}else{
						alternativaDAO.insert(a);
					}
				}
			}
		}
		
		if(entity.getAlternativaGabarito().getId() != null && entity.getAlternativaGabarito().getId() > 0){
			gabaritoDAO.insert(getGabaritoConfigurado(entity));
		}
	}

	@Override
	public List<Questao> findByEntityQuery(Questao entity) {
		return questaoDAO.findByEntityQuery(entity);
	}
	
}

package br.com.questoesconcursoadmin.daoImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import br.com.questoesconcursoadmin.dao.QuestaoDao;
import br.com.questoesconcursoadmin.model.Area;
import br.com.questoesconcursoadmin.model.Banca;
import br.com.questoesconcursoadmin.model.Cargo;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;
import br.com.questoesconcursoadmin.model.Categoria;
import br.com.questoesconcursoadmin.model.Concurso;
import br.com.questoesconcursoadmin.model.Disciplina;
import br.com.questoesconcursoadmin.model.Especialidade;
import br.com.questoesconcursoadmin.model.Gabarito;
import br.com.questoesconcursoadmin.model.Orgao;
import br.com.questoesconcursoadmin.model.Prova;
import br.com.questoesconcursoadmin.model.Questao;
import br.com.questoesconcursoadmin.model.QuestaoProva;
import br.com.questoesconcursoadmin.model.Ramo;
import br.com.questoesconcursoadmin.model.TextoDescritivo;

@Stateless
@LocalBean
public class QuestaoDaoImp extends GenericDAOJPAImpl<Questao, Long> implements QuestaoDao{
	
	@Override
	public List<Questao> findByEntity(Questao entity)throws PersistenceException {
		Session session = (Session) em.getDelegate();
		
		Criteria c = session.createCriteria(Questao.class, "e");
		Disjunction d = Restrictions.disjunction();
		
		c.createAlias("e.categoria", "c");
		c.createAlias("e.textoDescritivo", "enunciado");
		
		if(entity.getPalavraChave() != null && !"".equals(entity.getPalavraChave().trim())){
			d.add(Restrictions.ilike("e.questao", entity.getPalavraChave(), MatchMode.ANYWHERE));
			d.add(Restrictions.ilike("enunciado.texto", entity.getPalavraChave(), MatchMode.ANYWHERE));
		}
		
		if(entity.getCategoria()!= null && entity.getCategoria().getId() != null){
			c.add(Restrictions.eq("e.categoria", entity.getCategoria()));
		}
		
		if(entity.getCategoria()!= null && entity.getCategoria().getDisciplina() != null && entity.getCategoria().getDisciplina().getId() != null){
			c.add(Restrictions.eq("c.disciplina", entity.getCategoria().getDisciplina()));
		}
		
		if(entity.getTipoQuestao() != null && entity.getTipoQuestao() > 0){
			c.add(Restrictions.eq("e.tipoQuestao", entity.getTipoQuestao()));
		}
		
		c.add(d);
		
//		if(entity != null && entity.getProva() != null && entity.getProva().getCca() != null && entity.getProva().getCca().getIdConcurso() != null){
//			DetachedCriteria criteriaConcurso = DetachedCriteria.forClass(Concurso.class, "concurso");
//			criteriaConcurso.add(Restrictions.idEq(entity.getProva().getCca().getIdConcurso()));
//			c.add(Subqueries.exists(criteriaConcurso.setProjection(Projections.property("concurso.id"))));
//		}
		
		List<Questao> lista = (List<Questao>) c.list();
		return lista;
	}
	
	@Override
	public List<Questao> findByEntityQuery(Questao entity)throws PersistenceException {
		Query query = em.createQuery("" +
			"select " +
				"distinct questao.id, questao.questao, " +
				"texto.id, texto.texto, " +
				"gabarito.idQuestao, gabarito.idAlternativa," +
				"tipo.id, " +
				"prova.data, prova.id, " +
				"area.id, area.area, " +
				"cargo.id, cargo.nome, " +
				"especialidade.id, especialidade.descricao, " +
//				"ramo.id, ramo.ramo, " +
				"orgao.id, orgao.orgao, orgao.orgaoAbreviacao, " +
				"banca.id, banca.nome, banca.abreviacao, " +
				"concurso.id, concurso.ano, " +
				"disciplina.id, disciplina.nome, " +
				"categoria.id, categoria.nome " +
			"from " + 
				"Questao questao, Gabarito gabarito, TipoQuestao tipo, TextoDescritivo texto, " +
				"QuestaoProva qp, Prova prova, Area area, Cargo cargo, CargoConcursoArea cca, " +
//				"Ramo ramo, " +
				"Especialidade especialidade, Concurso concurso, Orgao orgao, " +
				"Banca banca, Categoria categoria, Disciplina disciplina " +
			"where " +
				"questao.id = gabarito.idQuestao " + 
			"and " +
				"questao.tipoQuestao = tipo.id " + 
			"and " +
				"questao.textoDescritivo.id = texto.id " +
			"and " +
				"qp.idQuestao = questao.id " +
			"and " +
				"qp.idProva = prova.id " +
			"and " +
				"cca.id = prova.cargoConcursoArea " +
			"and " +
				"area.id = cca.idArea " +
			"and " +
				"cargo.id = cca.idCargo " +
//			"and " +
//				"cca.ramo.id = ramo.id " +
			"and " +
				"cca.especialidade.id = especialidade.id " +
			"and " +
				"cca.idConcurso = concurso.id " +
			"and " +
				"concurso.orgao.id = orgao.id " +
			"and " +
				"concurso.banca.id = banca.id " +
			"and " +
				"categoria.disciplina.id = disciplina.id " +
			"and " +
				"questao.categoria.id = categoria.id  " +
			getCondicoes(entity) +
			" order by questao.id");
		
		
		//Configurar o retorno da consulta de acordo com a lógica abaixo
		List<Object[]> objects = query.getResultList();
		
		List<Questao> lista = configuraResultadoPesquisa(objects);
		
		return lista;
	}

	private List<Questao> configuraResultadoPesquisa(List<Object[]> objects) {
		List<Questao> lista = new ArrayList<Questao>();
		Questao questao = null;
		TextoDescritivo texto = null;
		Prova prova = null;
		CargoConcursoArea cca = null;
		
		Area area = null;
		Cargo cargo = null;
		Especialidade especialidade = null;
//		Ramo ramo = null;
		Orgao orgao = null;
		Banca banca = null;
		Concurso concurso = null;
		Categoria categoria = null;
		Disciplina disciplina = null;
		
		Gabarito gabarito = null;
		for (Object[] o : objects) {  
			Object[] aux = o;  
			questao = new Questao();
			texto = new TextoDescritivo();
			categoria = new Categoria();
			disciplina = new Disciplina();
			
			gabarito = new Gabarito();
			prova = new Prova();
			area = new Area();
			cargo = new Cargo();
			especialidade = new Especialidade();
//			ramo = new Ramo();
			orgao = new Orgao();
			banca = new Banca();
			concurso = new Concurso();
			
			cca = new CargoConcursoArea();
			
			questao.setId((Long)aux[0]);
			questao.setQuestao((String)aux[1]);
			texto.setId((Integer)aux[2]);
			texto.setTexto((String)aux[3]);
			gabarito.setIdQuestao((Long)aux[4]);
			gabarito.setIdAlternativa((Long)aux[5]);
			questao.setTipoQuestao((Long)aux[6]);
			prova.setData((Date)aux[7]);
			prova.setId((Integer)aux[8]);
			area.setId((Integer)aux[9]);
			area.setArea((String)aux[10]);
			cargo.setId((Integer)aux[11]);
			cargo.setNome((String)aux[12]);
			especialidade.setId((Integer)aux[13]);
			especialidade.setDescricao((String)aux[14]);
//			ramo.setId((Integer)aux[15]);
//			ramo.setRamo((String)aux[16]);
			orgao.setId((Long)aux[15]);
			orgao.setOrgao((String)aux[16]);
			orgao.setOrgaoAbreviacao((String)aux[17]);
			banca.setId((Integer)aux[18]);
			banca.setNome((String)aux[19]);
			banca.setAbreviacao((String)aux[20]);
			concurso.setId((Long)aux[21]);
			concurso.setAno((String)aux[22]);
			disciplina.setId((Integer)aux[23]);
			disciplina.setNome((String)aux[24]);
			categoria.setId((Integer)aux[25]);
			categoria.setNome((String)aux[26]);
			
			categoria.setDisciplina(disciplina);
//			ramo.setEspecialidade(especialidade);
			concurso.setOrgao(orgao);
			concurso.setBanca(banca);
			cca.setArea(area);
			cca.setIdArea(area.getId());
			cca.setCargo(cargo);
			cca.setIdCargo(cargo.getId());
			cca.setEspecialidade(especialidade);
//			cca.setRamo(ramo);
			cca.setIdConcurso(concurso.getId());
			cca.setConcurso(concurso);
			
			questao.setTextoDescritivo(texto);
			questao.setCategoria(categoria);
			prova.setCargoConcursoArea(cca.getId());
			prova.setCca(cca);
			questao.setProva(prova);
			lista.add(questao);  
		}
		return lista;
	}
	
	private String getCondicoes(Questao entity){
		
		String condicoes = "";
		
		if(entity.getPalavraChave() != null && !"".equals(entity.getPalavraChave().trim())){
			condicoes = condicoes + " and (questao.questao like '%" + entity.getPalavraChave() + "%' OR texto.texto like '%" + entity.getPalavraChave() + "%') ";
		}
		
		if(entity.getCategoria()!= null && entity.getCategoria().getId() != null){
			condicoes = condicoes + " and categoria.nome = '" + entity.getCategoria() + "' ";
		}
		
		if(entity.getCategoria()!= null && entity.getCategoria().getDisciplina() != null && entity.getCategoria().getDisciplina().getId() != null){
			condicoes = condicoes + " and disciplina.nome = '" + entity.getCategoria().getDisciplina() + "' ";
		}
		
		if(entity.getTipoQuestao() != null && entity.getTipoQuestao() > 0){
			condicoes = condicoes + " and questao.tipoQuestao = '" + entity.getTipoQuestao() + "' ";
		}
		
		return condicoes;
		
	}

}

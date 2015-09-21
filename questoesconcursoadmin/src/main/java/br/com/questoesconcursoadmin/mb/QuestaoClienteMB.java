package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;
import br.com.questoesconcursoadmin.model.Categoria;
import br.com.questoesconcursoadmin.model.Concurso;
import br.com.questoesconcursoadmin.model.Disciplina;
import br.com.questoesconcursoadmin.model.Prova;
import br.com.questoesconcursoadmin.model.Questao;
import br.com.questoesconcursoadmin.model.RepeatQuestaoPaginator;
import br.com.questoesconcursoadmin.remote.AlternativaRemote;
import br.com.questoesconcursoadmin.remote.QuestaoRemote;

@ManagedBean(name="questaoClienteMB")
@SessionScoped
public class QuestaoClienteMB extends QuestaoGeneric {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2936268117369636305L;
	
	@EJB AlternativaRemote alternativaRemote;
	
	private Concurso concursoPesquisa;
	private RepeatQuestaoPaginator paginator;
	
	private List<SelectItem> provasDisponiveis;
	private Boolean disableProvasDisponiveis = true;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.questaoRemote);
		setEntity(new Questao());
		setListaCategorias(carregaCategorias());
		setListaDisciplinas(carregaDisciplinas());
		setListaConcursos(carregaConcursos());
		carregaTiposQuestao();
	}
	
	@Override
	public String pesquisar() {
		long t1 = System.nanoTime();
		
		if(this.categoriaPesquisa != null && this.categoriaPesquisa.getId() != null){
			getEntity().setCategoria(this.categoriaPesquisa);
		}else{
			getEntity().setCategoria(new Categoria());
		}
		
		if(this.disciplinaPesquisa != null && this.disciplinaPesquisa.getId() != null){
			getEntity().getCategoria().setDisciplina(disciplinaPesquisa);
		}else{
			getEntity().getCategoria().setDisciplina(new Disciplina());
		}
		
		setEntityList(((QuestaoRemote) getEntityRemote()).findByEntityQuery(getEntity()));
		setRenderizaTabelaPesquia(true);
		
		paginator = new RepeatQuestaoPaginator(getEntityList(), alternativaRemote);
		long t2 = System.nanoTime();
		
		System.out.println("Tempo de execução: " + ((t2 - t1) * 1e-9) + " segundos");
		System.out.println(getEntityList().size() + " resultado");
		return null;
	}
	
	/**
	 * Método que carrega todos as provas de um concurso escolhido
	 * @param e
	 */
	public void onSelectConcurso(ValueChangeEvent e){
		if(e.getNewValue() != null && !(e.getNewValue().equals(""))){
			Concurso con = (Concurso) e.getNewValue();
			List<Prova> provas;
			try {
				List<CargoConcursoArea> ccas = ccaRemote.findByCodigoConcurso(con.getId());
				if(ccas != null){
					provas = new ArrayList<Prova>();
					Prova p = null;
					for(CargoConcursoArea cca : ccas){
						p = provaRemote.findyByCCAId(cca.getId());
						if(p == null){
							continue;
						}
						cca.setArea(areaRemote.recuperar(cca.getIdArea()));
						cca.setCargo(cargoRemote.recuperar(cca.getIdCargo()));
						p.setCca(cca);
						if(p != null){
							provas.add(p);
						}
					}
					
					this.provasDisponiveis = generateSelectItens(provas);
					this.disableProvasDisponiveis = false;
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}else{
			this.provasDisponiveis = null;
			this.disableProvasDisponiveis = true;
		}
	}
	
	public void visualizarEnunciado(ActionEvent actionEvent) {
        System.out.println(getEntity().getId());
    }
	
	public RepeatQuestaoPaginator getPaginator() {
		return paginator;
	}
	
	public String responder(){
		try {
			
			for(int i = 0; i < paginator.getModel().size(); i++){
				if(paginator.getModel().get(i).getId().equals(getEntity().getId())){
					paginator.getModel().get(i).setAlternativaGabarito(configuraGabarito());
					//TODO verificar se o usuário escolher a alternativa correta.
					paginator.getModel().get(i).setResposta(true);
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Concurso getConcursoPesquisa() {
		return concursoPesquisa;
	}

	public void setConcursoPesquisa(Concurso concursoPesquisa) {
		this.concursoPesquisa = concursoPesquisa;
	}

	public List<SelectItem> getProvasDisponiveis() {
		return provasDisponiveis;
	}

	public Boolean getDisableProvasDisponiveis() {
		return disableProvasDisponiveis;
	}

}

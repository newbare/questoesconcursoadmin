package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.questoesconcursoadmin.constantes.ConstantesNavegacao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;
import br.com.questoesconcursoadmin.model.Categoria;
import br.com.questoesconcursoadmin.model.Concurso;
import br.com.questoesconcursoadmin.model.Prova;
import br.com.questoesconcursoadmin.model.ProvaDataModel;
import br.com.questoesconcursoadmin.model.Questao;
import br.com.questoesconcursoadmin.model.TextoDescritivo;
import br.com.questoesconcursoadmin.model.constante.FormaInclusao;
import br.com.questoesconcursoadmin.model.constante.TipoQuestao;
import br.com.questoesconcursoadmin.model.datamodel.AlternativaDataModel;
import br.com.questoesconcursoadmin.remote.AlternativaRemote;

@ManagedBean(name="questaoMB")
@SessionScoped
public class QuestaoMB extends QuestaoGeneric {
	
	/**
	 * Constantes
	 */
	private static final long serialVersionUID = -1339996558952454386L;
	
	@EJB AlternativaRemote alternativaRemote;
	
//	private List<Questao> listaQuestao;
	private List<Questao> listaQuestaoCadastro;
	private List<SelectItem> listaBancas;
	private Questao questaoExclusao;
	
	private ProvaDataModel provasDisponiveis;
	
	private Concurso concurso;
	private Prova prova = new Prova();
	private Prova[] provasSelecionadas;
	
	private Boolean renderizaTabelaQuestoes = false;
	private Boolean desabilitaBotaoIncluirNaLista = false;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.questaoRemote);
		setEntity(new Questao());
		setListaCategorias(carregaCategorias());
		setListaDisciplinas(carregaDisciplinas());
		this.listaBancas = carregaBancas();
		setListaConcursos(carregaConcursos());
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
					
					this.provasDisponiveis = new ProvaDataModel(provas);
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}else{
			this.provasDisponiveis = null;
		}
	}
	
	public String salvarOuAlterar(){
		
		String retorno = null;
		
		if(!validaSalvarOuAlterar()){
			mostrarMensagemAtencao(getListaMensagens());
			return retorno;
		}
		
		if(this.alterar){
			retorno = alterar(this.listaQuestaoCadastro);
		}else{
			retorno = salvar(this.listaQuestaoCadastro);
		}
		
		removeBean();
		removeBeanDaClasse();
		
		return retorno;
	}
	
	private boolean validaSalvarOuAlterar(){
		boolean retorno = true;
		
		getListaMensagens().clear();
		if(this.listaQuestaoCadastro == null || this.listaQuestaoCadastro.size() < 1){
			retorno = false;
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_QUESTAO_OBRIGATORIA"));
		}
		return retorno;
	}
	
	/**
	 * Método que inclui em uma lista de questões para salvar posteriormente
	 */
	public void incluirNaLista(){
		if(!validaCamposObrigatorios()){
			mostrarMensagemAtencao(getListaMensagens());
			return;
		}
		
		if(this.listaQuestaoCadastro == null){
			this.listaQuestaoCadastro = new ArrayList<Questao>();
		}
		
		getEntity().setFormaInclusao(FormaInclusao.DIGITACAO.getCodigo());
		if(getRemoverQuebraLinhaQuestao()){
			getEntity().setQuestao(limparQuebraDeLinha(getEntity().getQuestao()));
		}
		
		if(getRemoverQuebraLinhaEnunciado()){
			getEntity().getTextoDescritivo().setTexto(limparQuebraDeLinha(getEntity().getTextoDescritivo().getTexto()));
		}
		
		this.renderizaTabelaQuestoes = true;
		this.listaQuestaoCadastro.add(getEntity());
		setRenderizaBotaoSalvar(true);
		if(alterar){
			desabilitaBotaoIncluirNaLista = true;
		}
		
		this.apagaCamposIncluirNaLista();
	}
	
	/**
	 * Método que apaga a questão selecionada da lista de questões a incluir
	 */
	public void excluirQuestaoDaLista(){
		
		for(int i = 0; i < this.listaQuestaoCadastro.size(); i++){
			if(this.questaoExclusao.equals(this.listaQuestaoCadastro.get(i))){
				this.listaQuestaoCadastro.remove(i);
				i--;
			}
		}
		if(this.listaQuestaoCadastro.size() == 0){
			this.renderizaTabelaQuestoes = false;
		}
		
	}
	
	/**
	 * Método que apaga as questões ao clicar no botão de incluir na lista
	 */
	private void apagaCamposIncluirNaLista(){
		
		Questao q = new Questao();
		q.setCategoria(configuraCategoria(getEntity().getCategoria()));
		q.setTextoDescritivo(configuraTextoDescritivo(getEntity().getTextoDescritivo()));
		q.setTipoQuestao(getEntity().getTipoQuestao());
		q.setProvas(getEntity().getProvas());
		if(getEntity().getNumero() != null && getEntity().getNumero() > 0){
			q.setNumero(getEntity().getNumero() + 1);
		}
		setEntity(q);
		
		setAlternativas(new AlternativaDataModel(q.getListaAlternativa()));
		setRenderizaTabelaAlternativas(false);
		
	}
	
	private TextoDescritivo configuraTextoDescritivo(TextoDescritivo textoDescritivo){
		TextoDescritivo texto = new TextoDescritivo();
		texto.setTexto(textoDescritivo.getTexto());
		texto.setReferencia(textoDescritivo.getReferencia());
		
		return texto;
	}
	
	private Categoria configuraCategoria(Categoria categoria){
		Categoria c = new Categoria();
		c = categoria;
		
		return c;
	}
	
	private boolean validaCamposObrigatorios(){
		
		boolean retorno = true;
		getListaMensagens().clear();
		
		if(getEntity().getCategoria().getNome() == null || "".equals(getEntity().getCategoria().getNome().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_MATERIA")));
			retorno = false;
		}
		
		if(getEntity().getTextoDescritivo().getTexto() == null || "".equals(getEntity().getTextoDescritivo().getTexto().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ENUNCIADO_QUESTAO")));
			retorno = false;
		}
		
		if(getEntity().getTipoQuestao() == null || getEntity().getTipoQuestao() <= 0){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_TIPO_QUESTAO")));
			retorno = false;
		}
		
		if(getEntity().getQuestao() == null || "".equals(getEntity().getQuestao().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_QUESTAO")));
			retorno = false;
		}
		
		return retorno;
		
	}
	
	/**
	 * Método que redireciona o usuário para a tela de cadastro de disciplina.
	 * 
	 * @return
	 */
	public String incluirDisciplina(){
		
		return ConstantesNavegacao.MANTER_DISCIPLINA;
	}
	
	/**
	 * Método que redireciona o usuário para a tela de cadastro de Matéria
	 * @return
	 */
	public String incluirCategoria(){
		
		return ConstantesNavegacao.MANTER_CATEGORIA;
	}
	
	/**
	 * Método que redireciona o usuário para a tela de cadastro de Prova
	 * @return
	 */
	public String incluirProva(){
		
		return null;
	}
	
	/**
	 * Método que redireciona o usuário para a tela de cadastro de Concurso
	 * @return
	 */
	public String incluirConcurso(){
		removeBeanDaClasse();
		return ConstantesNavegacao.MANTER_CONCURSO;
	}
	
	
	@Override
	public String editar() {
		
		try {
			
			getEntity().setAlternativaGabarito(configuraGabarito());
			
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_EDITAR"));
			return null;
		}
		
		if(getEntity().getTipoQuestao().equals(TipoQuestao.MULTIPLA_ESCOLHA.getCodigo())){
			setMultiplaEscolha(true);
			setCertoErrado(false);
			setRenderizaTabelaAlternativas(true);
			getEntity().setListaAlternativa(carregaAlternativasByQuestao(getEntity()));
			setAlternativas(new AlternativaDataModel(getEntity().getListaAlternativa()));
		}else if(getEntity().getTipoQuestao().equals(TipoQuestao.CERTO_ERRADO.getCodigo())){
			setMultiplaEscolha(false);
			setCertoErrado(true);
			setRenderizaTabelaAlternativas(false);
		}
		
		return super.editar();
	}

	/**
	 * Método que chama a tela de cadastro de compras após a escolha das provas.
	 * @return
	 */
	public String proximo(){
		if(!validasCamposCadastroProvaQuestao()){
			mostrarMensagemAtencao(getListaMensagens());
			return null;
		}
		getEntity().setProvas(provasSelecionadas);
		return ConstantesNavegacao.PROXIMO;
	}
	
	/**
	 * Valida campos na escolha das provas para cadastro de questões
	 * @return
	 */
	private boolean validasCamposCadastroProvaQuestao(){
		boolean retorno = true;
		getListaMensagens().clear();
		
		if(this.provasSelecionadas == null || this.provasSelecionadas.length <= 0){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_PROVA_OBRIGATORIA"));
			retorno = false;
		}
		
		
		return retorno;
	}
	
//	public List<Questao> getListaQuestao() {
//		return listaQuestao;
//	}
//
//	public void setListaQuestao(List<Questao> listaQuestao) {
//		this.listaQuestao = listaQuestao;
//	}

	public List<SelectItem> getListaBancas() {
		return listaBancas;
	}

	public void setListaBancas(List<SelectItem> listaBancas) {
		this.listaBancas = listaBancas;
	}

	public List<Questao> getListaQuestaoCadastro() {
		return listaQuestaoCadastro;
	}

	public void setListaQuestaoCadastro(List<Questao> listaQuestaoCadastro) {
		this.listaQuestaoCadastro = listaQuestaoCadastro;
	}

	public Boolean getRenderizaTabelaQuestoes() {
		return renderizaTabelaQuestoes;
	}

	public void setRenderizaTabelaQuestoes(Boolean renderizaTabelaQuestoes) {
		this.renderizaTabelaQuestoes = renderizaTabelaQuestoes;
	}

	public Boolean getDesabilitaBotaoIncluirNaLista() {
		return desabilitaBotaoIncluirNaLista;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Prova[] getProvasSelecionadas() {
		return provasSelecionadas;
	}

	public void setProvasSelecionadas(Prova[] provasSelecionadas) {
		this.provasSelecionadas = provasSelecionadas;
	}

	public Questao getQuestaoExclusao() {
		return questaoExclusao;
	}

	public void setQuestaoExclusao(Questao questaoExclusao) {
		this.questaoExclusao = questaoExclusao;
	}

	public ProvaDataModel getProvasDisponiveis() {
		return provasDisponiveis;
	}
}

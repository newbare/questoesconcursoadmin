package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Alternativa;
import br.com.questoesconcursoadmin.model.Banca;
import br.com.questoesconcursoadmin.model.Categoria;
import br.com.questoesconcursoadmin.model.Concurso;
import br.com.questoesconcursoadmin.model.Disciplina;
import br.com.questoesconcursoadmin.model.Gabarito;
import br.com.questoesconcursoadmin.model.Questao;
import br.com.questoesconcursoadmin.model.constante.CertoErrado;
import br.com.questoesconcursoadmin.model.constante.TipoQuestao;
import br.com.questoesconcursoadmin.model.datamodel.AlternativaDataModel;
import br.com.questoesconcursoadmin.remote.AlternativaRemote;
import br.com.questoesconcursoadmin.remote.AreaRemote;
import br.com.questoesconcursoadmin.remote.BancaRemote;
import br.com.questoesconcursoadmin.remote.CargoConcursoAreaRemote;
import br.com.questoesconcursoadmin.remote.CargoRemote;
import br.com.questoesconcursoadmin.remote.CategoriaRemote;
import br.com.questoesconcursoadmin.remote.ConcursoRemote;
import br.com.questoesconcursoadmin.remote.DisciplinaRemote;
import br.com.questoesconcursoadmin.remote.GabaritoRemote;
import br.com.questoesconcursoadmin.remote.ProvaRemote;
import br.com.questoesconcursoadmin.remote.QuestaoRemote;

public class QuestaoGeneric extends GenericMB<Questao>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6573982735323673779L;
	
	@EJB CategoriaRemote categoriaRemote;
	@EJB DisciplinaRemote disciplinaRemote;
	@EJB BancaRemote bancaRemote;
	@EJB AlternativaRemote alternativaRemote;
	@EJB ConcursoRemote concursoRemote;
	@EJB QuestaoRemote questaoRemote;
	@EJB GabaritoRemote gabaritoRemote;
	@EJB CargoConcursoAreaRemote ccaRemote;
	@EJB ProvaRemote provaRemote;
	@EJB AreaRemote areaRemote;
	@EJB CargoRemote cargoRemote;
	
	private Boolean multiplaEscolha = false;
	private Boolean certoErrado = false;
	private Boolean renderizaTabelaAlternativas = false;
	private Boolean renderizaBotaoSalvar = false;
	private Boolean removerQuebraLinhaEnunciado = false;
	private Boolean removerQuebraLinhaQuestao = false;
	private Boolean removerQuebraLinhaAlternativa = false;
	private Boolean disableMateria = true;
	
	Disciplina disciplinaPesquisa;
	Categoria categoriaPesquisa;
	
	private String alternativa;
	
	private AlternativaDataModel alternativas;
	
	private List<SelectItem> listaCategorias;
	private List<SelectItem> listaDisciplinas;
	private List<SelectItem> listaConcursos;
	private List<SelectItem> tiposQuestao;
	
	public String pesquisar(){
		
		try {
			
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
			
			setEntityList(getEntityRemote().recuperarPorEntidade(getEntity()));
			if(getEntityList() != null && getEntityList().size() > 0){
				setRenderizaTabelaPesquia(true);
			}else{
				mostrarMensagemInformacao(getMessageBundle("Mensagem.MSG_INFORMATIVA_NENHUM_REGISTRO_ENCONTRADO"));
				setRenderizaTabelaPesquia(false);
			}
			
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_PESQUISA"));
			return null;
		}
		
		return null;
	}
	
	/**
	 * Método que mostra os dados na tela de acordo com o tipo de questão selecionado
	 * @param e
	 */
	public void onSelectTipoQuestao(ValueChangeEvent e){
		if(e.getNewValue() != null && !(e.getNewValue().equals(""))){
			if(e.getNewValue().toString().equals(TipoQuestao.CERTO_ERRADO.getCodigo().toString())){
				this.certoErrado = true;
				this.multiplaEscolha = false;
			} else if(e.getNewValue().toString().equals(TipoQuestao.MULTIPLA_ESCOLHA.getCodigo().toString())){
				this.multiplaEscolha = true;
				this.certoErrado = false;
			}
		}
	}
	
	/**
	 * Método que carrega as categorias de acordo com a disciplina selecionada
	 * @param e
	 */
	public void onSelectDisciplina(ValueChangeEvent e){
		if(e.getNewValue() != null && !(e.getNewValue().equals(""))){
			Disciplina d = (Disciplina) e.getNewValue();
			Categoria c = new Categoria();
			c.setDisciplina(d);
			try {
				List<Categoria> lista = categoriaRemote.recuperarPorEntidade(c);
				if(lista != null){
					this.listaCategorias = generateSelectItens(lista);
					this.disableMateria = false;
				}
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}else{
//			this.listaCategorias = carregaCategorias();
			this.listaCategorias = new ArrayList<SelectItem>();
			this.categoriaPesquisa = null;
			this.disableMateria = true;
		}
	}
	
	/**
	 * Método que recupera e configura o gabarito de uma questão ao editar.
	 * @throws BusinessException
	 */
	protected Alternativa configuraGabarito() throws BusinessException {
		Gabarito g = gabaritoRemote.findGabaritoByQuestion(getEntity().getId());
		Alternativa a = null;
		if(g != null && g.getIdAlternativa() != null && g.getIdAlternativa() > 0){
			a = new Alternativa();
			a.setId(g.getIdAlternativa());
			a = alternativaRemote.findOneByEntity(a);
		}
		
		return a;
	}
	
	/**
	 * Método que configura e retorna a alternativa para inclusão na lista de alternativas de uma questão.
	 *  
	 * @return {@link Alternativa}
	 */
	private Alternativa getAlternativaConfigurada(){
		Alternativa alternativa = new Alternativa();
		if(this.removerQuebraLinhaAlternativa){
			this.alternativa = limparQuebraDeLinha(this.alternativa);
		}
		alternativa.setAlternativa(this.alternativa);
		return alternativa;
	}
	
	/**
	 * Valida o campo alternativa na inclusão
	 * 
	 * @return {@link Boolean}
	 */
	private boolean validaInclusaoAlternativa(){
		boolean retorno = true;
		getListaMensagens().clear();
		
		if(this.alternativa == null || "".equals(this.alternativa.trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ALTERNATIVA")));
			retorno = false;
		}
		
		return retorno;
	}
	
	/**
	 * Apaga campo Alternativa ao inserir uma alternativa.
	 * 
	 */
	private void apagaCampoIncluirAlternativa(){
		this.alternativa = null;
	}
	
	/**
	 * Inclui alternativa de uma questão na lista de alternativas
	 * 
	 */
	public void incluirAlternativa(){
		
		if(!validaInclusaoAlternativa()){
			mostrarMensagemAtencao(getListaMensagens());
			return;
		}
		
		getEntity().getListaAlternativa().add(getAlternativaConfigurada());
		this.alternativas = new AlternativaDataModel(getEntity().getListaAlternativa());
		this.renderizaTabelaAlternativas = true;
		apagaCampoIncluirAlternativa();
	}
	
	/**
	 * Limpa as quebras de linha de um texto
	 * @param texto
	 * @return
	 */
	protected String limparQuebraDeLinha(String texto){
		return texto.replaceAll("\r|\n\r|\n", " ").replaceAll("  ", " ");
	}
	
	/**
	 * Método que recupera todas as categorias e converte para uma lista de SelectItem
	 */
	protected List<SelectItem> carregaCategorias(){
		try {
			List<Categoria> lista = categoriaRemote.recuperarTodos();
			if(lista != null){
				return generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return new ArrayList<SelectItem>();
	}
	
	/**
	 * Método que recupera todas as disciplinas e converte para uma lista de SelectItem
	 */
	protected List<SelectItem> carregaDisciplinas(){
		try {
			List<Disciplina> lista = disciplinaRemote.recuperarTodos();
			if(lista != null){
				return generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return new ArrayList<SelectItem>();
	}
	
	/**
	 * Método que recupera todas as bancas e converte para uma lista de SelectItem
	 */
	protected List<SelectItem> carregaBancas(){
		try {
			List<Banca> lista = bancaRemote.recuperarTodos();
			if(lista != null){
				return generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return new ArrayList<SelectItem>();
	}
	
	/**
	 * Método que recupera todas as bancas e converte para uma lista de SelectItem
	 */
	List<Alternativa> carregaAlternativasByQuestao(Questao questao){
		try {
			Alternativa alternativa = new Alternativa();
			alternativa.setQuestao(questao);
			List<Alternativa> lista = alternativaRemote.recuperarPorEntidade(alternativa);
			if(lista != null){
				return lista;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return new ArrayList<Alternativa>();
	}
	
	/**
	 * Método que recupera todas os concusos e converte para uma lista de SelectItem
	 */
	protected List<SelectItem> carregaConcursos(){
		try {
			List<Concurso> lista = concursoRemote.recuperarTodos();
			if(lista != null){
				return generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return new ArrayList<SelectItem>();
	}
	
	/**
	 * Método que monta a lista de tipos de questao e converte para uma lista de SelectItem
	 */
	protected void carregaTiposQuestao(){
		this.tiposQuestao = new ArrayList<SelectItem>();
		this.tiposQuestao.add(new SelectItem(getTipoQuestaoCertoErrado(), getMessageBundle("LBL_CERTO_ERRADO")));
		this.tiposQuestao.add(new SelectItem(getTipoQuestaoMultiplaEscolha(), getMessageBundle("LBL_MULTIPLA_ESCOLHA")));
	}
	
	public Boolean getCertoErrado() {
		return certoErrado;
	}

	public void setCertoErrado(Boolean certoErrado) {
		this.certoErrado = certoErrado;
	}

	public Boolean getMultiplaEscolha() {
		return multiplaEscolha;
	}

	public void setMultiplaEscolha(Boolean multiplaEscolha) {
		this.multiplaEscolha = multiplaEscolha;
	}
	
	public String getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}

	public AlternativaDataModel getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(AlternativaDataModel alternativas) {
		this.alternativas = alternativas;
	}
	
	public Boolean getRenderizaTabelaAlternativas() {
		return renderizaTabelaAlternativas;
	}

	public void setRenderizaTabelaAlternativas(Boolean renderizaTabelaAlternativas) {
		this.renderizaTabelaAlternativas = renderizaTabelaAlternativas;
	}

	public Boolean getRenderizaBotaoSalvar() {
		return renderizaBotaoSalvar;
	}

	public void setRenderizaBotaoSalvar(Boolean renderizaBotaoSalvar) {
		this.renderizaBotaoSalvar = renderizaBotaoSalvar;
	}

	public Boolean getRemoverQuebraLinhaEnunciado() {
		return removerQuebraLinhaEnunciado;
	}

	public void setRemoverQuebraLinhaEnunciado(Boolean removerQuebraLinhaEnunciado) {
		this.removerQuebraLinhaEnunciado = removerQuebraLinhaEnunciado;
	}

	public Boolean getRemoverQuebraLinhaQuestao() {
		return removerQuebraLinhaQuestao;
	}

	public void setRemoverQuebraLinhaQuestao(Boolean removerQuebraLinhaQuestao) {
		this.removerQuebraLinhaQuestao = removerQuebraLinhaQuestao;
	}

	public Boolean getRemoverQuebraLinhaAlternativa() {
		return removerQuebraLinhaAlternativa;
	}

	public void setRemoverQuebraLinhaAlternativa(
			Boolean removerQuebraLinhaAlternativa) {
		this.removerQuebraLinhaAlternativa = removerQuebraLinhaAlternativa;
	}

	public Boolean getDisableMateria() {
		return disableMateria;
	}

	public void setDisableMateria(Boolean disableMateria) {
		this.disableMateria = disableMateria;
	}

	public List<SelectItem> getListaCategorias() {
		return listaCategorias;
	}

	public void setListaCategorias(List<SelectItem> listaCategorias) {
		this.listaCategorias = listaCategorias;
	}

	public List<SelectItem> getListaDisciplinas() {
		return listaDisciplinas;
	}

	public void setListaDisciplinas(List<SelectItem> listaDisciplinas) {
		this.listaDisciplinas = listaDisciplinas;
	}
	
	public Categoria getCategoriaPesquisa() {
		return categoriaPesquisa;
	}

	public void setCategoriaPesquisa(Categoria categoriaPesquisa) {
		this.categoriaPesquisa = categoriaPesquisa;
	}

	public Disciplina getDisciplinaPesquisa() {
		return disciplinaPesquisa;
	}

	public void setDisciplinaPesquisa(Disciplina disciplinaPesquisa) {
		this.disciplinaPesquisa = disciplinaPesquisa;
	}
	
	public Long getCertoCodigo() {
		return CertoErrado.CERTO.getCodigo();
	}

	public Long getErradoCodigo() {
		return CertoErrado.ERRADO.getCodigo();
	}
	
	public Long getAnuladaCodigo() {
		return CertoErrado.ANULADA.getCodigo();
	}
	
	public Long getTipoQuestaoCertoErrado(){
		return TipoQuestao.CERTO_ERRADO.getCodigo();
	}
	
	public Long getTipoQuestaoMultiplaEscolha(){
		return TipoQuestao.MULTIPLA_ESCOLHA.getCodigo();
	}

	public List<SelectItem> getListaConcursos() {
		return listaConcursos;
	}

	public void setListaConcursos(List<SelectItem> listaConcursos) {
		this.listaConcursos = listaConcursos;
	}

	public List<SelectItem> getTiposQuestao() {
		return tiposQuestao;
	}

}

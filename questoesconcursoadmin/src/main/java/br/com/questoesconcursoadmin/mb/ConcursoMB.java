package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import br.com.questoesconcursoadmin.constantes.ConstantesNavegacao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Area;
import br.com.questoesconcursoadmin.model.Banca;
import br.com.questoesconcursoadmin.model.Cargo;
import br.com.questoesconcursoadmin.model.CargoConcursoArea;
import br.com.questoesconcursoadmin.model.Concurso;
import br.com.questoesconcursoadmin.model.Especialidade;
import br.com.questoesconcursoadmin.model.Orgao;
import br.com.questoesconcursoadmin.model.Ramo;
import br.com.questoesconcursoadmin.remote.AreaRemote;
import br.com.questoesconcursoadmin.remote.BancaRemote;
import br.com.questoesconcursoadmin.remote.CargoConcursoAreaRemote;
import br.com.questoesconcursoadmin.remote.CargoRemote;
import br.com.questoesconcursoadmin.remote.ConcursoRemote;
import br.com.questoesconcursoadmin.remote.EspecialidadeRemote;
import br.com.questoesconcursoadmin.remote.OrgaoRemote;
import br.com.questoesconcursoadmin.remote.RamoRemote;

@ManagedBean(name="concursoMB")
@SessionScoped
public class ConcursoMB extends GenericMB<Concurso> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3454613396325719014L;
	
	@EJB ConcursoRemote concursoRemote;
	@EJB BancaRemote bancaRemote;
	@EJB OrgaoRemote orgaoRemote;
	@EJB AreaRemote areaRemote;
	@EJB CargoRemote cargoRemote;
	@EJB EspecialidadeRemote especialidadeRemote;
	@EJB CargoConcursoAreaRemote ccaRemote;
	@EJB RamoRemote ramoRemote;
	
	private List<SelectItem> listaBanca;
	private List<SelectItem> listaOrgao;
	private List<SelectItem> listaArea;
	private List<SelectItem> listaEspecialidade;
	private List<SelectItem> listaRamo;
	private List<SelectItem> listaCargo;
	
	private Boolean renderizaTabelaAreaCargoEspecializacao = false;
	private Cargo cargo;
	private Especialidade especialidade;
	private Ramo ramo;
	private Area area;
	private CargoConcursoArea cargoConcursoAreaExclusao;
	
	@PostConstruct
	@Override
	public void init() {
		super.init();
		setEntityRemote(this.concursoRemote);
		setEntity(new Concurso());
		setEntityList(new ArrayList<Concurso>());
		carregaBanca();
		carregaOrgao();
	}
	
	/**
	 * Método que carrega e configura a lista com todas as bancas disponíveis na base de dados
	 */
	private void carregaBanca(){
		try {
			List<Banca> lista = bancaRemote.recuperarTodos();
			if(lista != null){
				this.listaBanca = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que carrega e configura a lista com todas os orgãos disponíveis na base de dados
	 */
	private void carregaOrgao(){
		try {
			List<Orgao> lista = orgaoRemote.recuperarTodos();
			if(lista != null){
				this.listaOrgao = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que carrega e configura a lista com todas as areas disponíveis na base de dados
	 */
	private void carregaArea(){
		try {
			List<Area> lista = areaRemote.recuperarTodos();
			if(lista != null){
				this.listaArea = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que carrega e configura a lista com todas os cargos disponíveis na base de dados
	 */
	private void carregaCargo(){
		try {
			List<Cargo> lista = cargoRemote.recuperarTodos();
			if(lista != null){
				this.listaCargo = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que carrega e configura a lista com todas as especialidades disponíveis na base de dados
	 */
	private void carregaEspecialidade(){
		try {
			List<Especialidade> lista = especialidadeRemote.recuperarTodos();
			if(lista != null){
				this.listaEspecialidade = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Método que carrega e configura a lista com todas os ramos disponíveis na base de dados
	 */
	private void carregaRamo(){
		try {
			List<Ramo> lista = ramoRemote.recuperarTodos();
			if(lista != null){
				this.listaRamo = generateSelectItens(lista);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
	}
	
	public String salvarOuAlterar(){
		
		if(alterar){
			return alterar();
		}else{
			return salvar();
		}
	}
	
	public String adicionarAreaCargoConcurso(){
		
		if(!validaAdicionarAreaCargoConcurso()){
			mostrarMensagemAtencao(getListaMensagens());
			return null;
		}
		
		configuraEntidadeConcurso();
		apagaCamposCargoConcursoArea();
		this.renderizaTabelaAreaCargoEspecializacao = true;
		
		return null;
	}
	
	private void configuraEntidadeConcurso(){
		CargoConcursoArea cca = new CargoConcursoArea();
		cca.setEspecialidade(this.especialidade);
		cca.setRamo(this.ramo);
		cca.setIdArea(getArea().getId());
		cca.setArea(getArea());
		cca.setIdCargo(getCargo().getId());
		cca.setCargo(getCargo());
		cca.setRamo(this.ramo);
		
		getEntity().getListaCargoConcursoArea().add(cca);
	}
	
	private void apagaCamposCargoConcursoArea(){
		setArea(null);
		setCargo(null);
		setEspecialidade(null);
		setRamo(null);
	}
	
	public void excluirCargoAreaConcurso(){
		for(int i = 0; i < getEntity().getListaCargoConcursoArea().size(); i++){
			if(this.cargoConcursoAreaExclusao.equals(getEntity().getListaCargoConcursoArea().get(i))){
				getEntity().getListaCargoConcursoArea().remove(i);
				i--;
			}
		}
		if(getEntity().getListaCargoConcursoArea().size() == 0){
			this.renderizaTabelaAreaCargoEspecializacao = false;
		}
	}
	
	private boolean validaCamposObrigatorios(){
		
		boolean retorno = true;
		getListaMensagens().clear();
		
		if(getEntity().getOrgao() == null || getEntity().getOrgao().getId() < 1){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ORGAO")));
			retorno = false;
		}
		
		if(getEntity().getBanca() == null || getEntity().getBanca().getId() < 1){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_BANCA")));
			retorno = false;
		}
		
		if(getEntity().getAno() == null || "".equals(getEntity().getAno().trim())){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_ANO")));
			retorno = false;
		}else if(getEntity().getAno().length() != 4){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_ANO_INVALIDO"));
			retorno = false;
		}
		
		return retorno;
		
	}
	
	private boolean validaAdicionarAreaCargoConcurso(){
		boolean retorno = true;
		getListaMensagens().clear();
		
		if(getArea() == null || getArea().getId() < 1){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_AREA")));
			retorno = false;
		}
		
		if(getCargo() == null || getCargo().getId() < 1){
			getListaMensagens().add(getMessageBundle("Mensagem.MSG_CAMPO_OBRIGATORIO").replace("{0}", getMessageBundle("LBL_CARGO")));
			retorno = false;
		}
		
		if(retorno){
			for(CargoConcursoArea cca : getEntity().getListaCargoConcursoArea()){
				if((cca.getEspecialidade() == this.especialidade) && 
						(cca.getIdArea().equals(this.area.getId())) && 
						(cca.getIdCargo().equals(this.cargo.getId()) &&
						(cca.getRamo() == this.ramo))){
					
					getListaMensagens().add(getMessageBundle("Mensagem.MSG_CARGO_AREA_ESPECIALIDADE_JA_ADICIONADOS"));
					retorno = false;
					
				}
			}
		}
		
		return retorno;
	}
	
	public String incluirBanca(){
		removeBeanDaClasse();
		return ConstantesNavegacao.MANTER_BANCA;
	}
	
	public String incluirOrgao(){
		removeBeanDaClasse();
		return ConstantesNavegacao.MANTER_ORGAO;
	}
	
	public String incluirArea(){
		return ConstantesNavegacao.MANTER_AREA;
	}
	
	public String incluirEspecialidade(){
		return ConstantesNavegacao.MANTER_ESPECIALIDADE;
	}
	
	public String incluirRamo(){
		return ConstantesNavegacao.MANTER_RAMO;
	}
	
	public String incluirCargo(){
		return ConstantesNavegacao.MANTER_CARGO;
	}
	
	public String proximo(){
		if(!validaCamposObrigatorios()){
			mostrarMensagemAtencao(getListaMensagens());
			return null;
		}
		
		carregaArea();
		carregaCargo();
		carregaEspecialidade();
		carregaRamo();
		
		if(alterar){
			
			try {
				getEntity().setListaCargoConcursoArea(ccaRemote.findByCodigoConcurso(getEntity().getId()));
				
				if(getEntity().getListaCargoConcursoArea() != null && getEntity().getListaCargoConcursoArea().size() > 0){
					for(CargoConcursoArea cca : getEntity().getListaCargoConcursoArea()){
						cca.setCargo(cargoRemote.recuperar(cca.getIdCargo()));
						cca.setArea(areaRemote.recuperar(cca.getIdArea()));
					}
					this.renderizaTabelaAreaCargoEspecializacao = true;
				}
				
			} catch (BusinessException e) {
				e.printStackTrace();
			}
		}
		
		return ConstantesNavegacao.PROXIMO;
	}

	public List<SelectItem> getListaBanca() {
		return listaBanca;
	}

	public List<SelectItem> getListaOrgao() {
		return listaOrgao;
	}

	public List<SelectItem> getListaArea() {
		return listaArea;
	}

	public List<SelectItem> getListaEspecialidade() {
		return listaEspecialidade;
	}

	public List<SelectItem> getListaCargo() {
		return listaCargo;
	}

	public Boolean getRenderizaTabelaAreaCargoEspecializacao() {
		return renderizaTabelaAreaCargoEspecializacao;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public CargoConcursoArea getCargoConcursoAreaExclusao() {
		return cargoConcursoAreaExclusao;
	}

	public void setCargoConcursoAreaExclusao(
			CargoConcursoArea cargoConcursoAreaExclusao) {
		this.cargoConcursoAreaExclusao = cargoConcursoAreaExclusao;
	}

	public List<SelectItem> getListaRamo() {
		return listaRamo;
	}

	public Ramo getRamo() {
		return ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}

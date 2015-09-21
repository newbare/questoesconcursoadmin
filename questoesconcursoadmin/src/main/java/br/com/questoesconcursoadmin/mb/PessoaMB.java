package br.com.questoesconcursoadmin.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DualListModel;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import br.com.questoesconcursoadmin.constantes.ConstantesNavegacao;
import br.com.questoesconcursoadmin.exception.BusinessException;
import br.com.questoesconcursoadmin.model.Perfil;
import br.com.questoesconcursoadmin.model.Pessoa;
import br.com.questoesconcursoadmin.model.Usuario;
import br.com.questoesconcursoadmin.model.UsuarioPerfil;
import br.com.questoesconcursoadmin.remote.PerfilRemote;
import br.com.questoesconcursoadmin.remote.PessoaRemote;
import br.com.questoesconcursoadmin.remote.UsuarioPerfilRemote;

@ManagedBean(name="pessoaMB")
@SessionScoped
public class PessoaMB extends GenericMB<Pessoa> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8362031517110112284L;

	@EJB
	PessoaRemote pessoaRemote;
	@EJB
	PerfilRemote perfilRemote;
	@EJB
	UsuarioPerfilRemote usuarioPerfilRemote;

	private Usuario usuario;
	private Pessoa pessoa;
	private String password;
	private String confirmarPassword;
	private DualListModel<String> perfis;
	
	//Campos de pesquisa
	private String nomePesquisa;
	private String cpfPesquisa;
	private String emailPesquisa;
	private List<Pessoa> listaPessoa;
	private Pessoa pessoaExclusao;
	
	@PostConstruct
	public void init(){
		super.init();
		usuario = new Usuario();
		pessoa = new Pessoa();
		configuraPickListPerfis(configuraPerfis(recuperaPerfis()), configuraPerfis(null));
	}
	
	/**
	 * Recupera lista de perfis
	 */
	private List<Perfil> recuperaPerfis(){
		List<Perfil> perfis = new ArrayList<Perfil>();
		try {
			perfis = perfilRemote.recuperarTodos();
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_CONSULTA_PERFIL"));
			return perfis;
		}
		return perfis;
	}
	
	private List<String> configuraPerfis(List<Perfil> perfis){
		
		List<String> perfisConfigurado = new ArrayList<String>();
		if(perfis != null){
			for(Perfil p : perfis){
				perfisConfigurado.add(p.getAuthority());
			}
		}
		
		return perfisConfigurado;
	}
	
	private List<String> configuraPerfisWithUsuarioPerfis(List<UsuarioPerfil> usuariosPerfis){
		
		List<String> perfisConfigurado = new ArrayList<String>();
		try{
			if(usuariosPerfis != null){
				for(UsuarioPerfil up : usuariosPerfis){
					perfisConfigurado.add(perfilRemote.recuperar(up.getIdPerfil()).getAuthority());
				}
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_CONSULTA_PERFIL"));
			return perfisConfigurado;
		}
		return perfisConfigurado;
	}
	
	private void configuraPickListPerfis(List<String> perfisOrigem, List<String> perfisDestino){
		this.perfis = new DualListModel<String>(perfisOrigem, perfisDestino);
	}
	
	public String salvarOuAlterar(){
		if(alterar){
			return alterar();
		}else{
			return salvar();
		}
	}
	
	public String pesquisar(){
		
		try{
			Pessoa p = new Pessoa();
			Usuario u = new Usuario();
			u.setEmail(this.emailPesquisa);
			p.setNome(this.nomePesquisa);
			p.setUsuario(u);
			p.setCpf(this.cpfPesquisa);
			
			this.listaPessoa = pessoaRemote.findByEntity(p);
			if(this.listaPessoa != null && this.listaPessoa.size() > 0){
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
	
	public String excluir(){
		
		try {
			pessoaRemote.deletar(pessoaExclusao);
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_DELETAR"));
			return null;
		} catch (EJBTransactionRolledbackException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_DELETAR_CONSTRAINT"));
			return null;
		}
		
		mostrarMensagemInformacao(getMessageBundle("Mensagem.MSG_SUCESSO_REGISTRO_EXCLUIDO"));
		return pesquisar();
	}
	
	public String editar(){
		
		this.usuario = this.pessoa.getUsuario();
		List<UsuarioPerfil> perfisUsuario = recuperaPerfisUsuario(this.usuario.getId());
		configuraPickListPerfis(configuraPerfis(getPerfisNaoAtribuidosAoUsuario(perfisUsuario)), 
				configuraPerfisWithUsuarioPerfis(perfisUsuario));
		
		return super.editar();
		
	}
	
	private List<Perfil> getPerfisNaoAtribuidosAoUsuario(List<UsuarioPerfil> perfisUsuario){
		
		List<Perfil> todosPerfis = recuperaPerfis();
		for(UsuarioPerfil up : perfisUsuario){
			
			for(int i = 0; i < todosPerfis.size(); i++){
				if(up.getIdPerfil().equals(todosPerfis.get(i).getId())){
					todosPerfis.remove(i);
					i--;
				}
			}
		}
		
		return todosPerfis;
		
	}
	
	private List<UsuarioPerfil> recuperaPerfisUsuario(Integer idUsuario){
		try {
			return usuarioPerfilRemote.findByCodigoUsuario(this.usuario.getId());
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_CONSULTA_PERFIL"));
			return null;
		}
	}
	
	public String alterar(){
		
		try{
			if(!validarEmail(usuario.getEmail())){
				mostrarMensagemErro(getListaMensagens());
				return null;
			}
			
			if(!validaPassword()){
				mostrarMensagemErro(getListaMensagens());
				return null;
			}
			
			configuraPerfisSelecionados();
			this.usuario.setSenha(new Md5PasswordEncoder().encodePassword(this.password, null));
			this.usuario.setEnable(true);
			this.pessoa.setUsuario(this.usuario);
	
			pessoaRemote.alterar(this.pessoa);
			this.alterar = false;
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_SALVAR"));
			return null;
		}
		return ConstantesNavegacao.ALTERAR;
	}
	
	/**
	 * Método que recebe ação de salvar da tela
	 * 
	 * @return
	 */
	public String salvar() {
		
		try{
			if(!validarEmail(usuario.getEmail())){
				mostrarMensagemErro(getListaMensagens());
				return null;
			}
			
			if(!validaPassword()){
				mostrarMensagemErro(getListaMensagens());
				return null;
			}
			
			configuraPerfisSelecionados();
			this.usuario.setSenha(new Md5PasswordEncoder().encodePassword(password, null));
			this.usuario.setEnable(true);
			this.pessoa.setUsuario(this.usuario);

			pessoaRemote.inserir(this.pessoa);
		} catch (BusinessException e) {
			e.printStackTrace();
			mostrarMensagemErro(getMessageBundle("Mensagem.MSG_ERRO_SALVAR"));
			return null;
		}
		return ConstantesNavegacao.SALVAR;
	}
	
	/**
	 * Método que verifica se o password e a confirmação de password são iguais e respeitam as regras de validação de senha
	 * 
	 * @return
	 */
	private boolean validaPassword(){
		if(this.password.equals(this.confirmarPassword)){
			return true;
		}else{
			getListaMensagens().clear();
			getListaMensagens().add("Confirmação da senha incorreta!");
			return false;
		}
	}
	
	/**
	 * Configura os perfis selecionados para o usuário
	 * 
	 */
	private void configuraPerfisSelecionados(){
		if(this.perfis != null){
			this.usuario.setPerfis(this.perfis.getTarget());
		}
	}

	/* Getters e Setters */
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmarPassword() {
		return confirmarPassword;
	}

	public void setConfirmarPassword(String confirmarPassword) {
		this.confirmarPassword = confirmarPassword;
	}

	public DualListModel<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(DualListModel<String> perfis) {
		this.perfis = perfis;
	}

	public String getNomePesquisa() {
		return nomePesquisa;
	}

	public void setNomePesquisa(String nomePesquisa) {
		this.nomePesquisa = nomePesquisa;
	}

	public String getCpfPesquisa() {
		return cpfPesquisa;
	}

	public void setCpfPesquisa(String cpfPesquisa) {
		this.cpfPesquisa = cpfPesquisa.replaceAll("\\W", "");
	}

	public String getEmailPesquisa() {
		return emailPesquisa;
	}

	public void setEmailPesquisa(String emailPesquisa) {
		this.emailPesquisa = emailPesquisa;
	}

	public List<Pessoa> getListaPessoa() {
		return listaPessoa;
	}

	public void setListaPessoa(List<Pessoa> listaPessoa) {
		this.listaPessoa = listaPessoa;
	}

	public Pessoa getPessoaExclusao() {
		return pessoaExclusao;
	}

	public void setPessoaExclusao(Pessoa pessoaExclusao) {
		this.pessoaExclusao = pessoaExclusao;
	}

}

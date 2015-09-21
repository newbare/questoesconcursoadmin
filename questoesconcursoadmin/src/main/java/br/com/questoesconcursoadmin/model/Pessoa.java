package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SEG_PESSOA", schema = "dbo")
public class Pessoa implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7454072916496029787L;
	
	@Id
	@SequenceGenerator(name="pessoa_sequence",sequenceName="dbo.pessoa_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pessoa_sequence")
	@Column(name = "pes_id",unique=true,nullable=false)
	private Integer id;
	
	@Column(name = "pes_nome", length=200, nullable=false)
	private String nome;
	
	@Column(name = "pes_cpf", unique=true)
	private String cpf;
	
	@Column(name = "pes_receber_noticias")
	private boolean receberNoticia;
	
	@Column(name = "pes_ultimas_atualizacoes")
	private boolean ultimasAtualizacoes;
	
	@ManyToOne
	@JoinColumn(name="usu_id")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replaceAll("\\W", "");
	}

	public boolean isReceberNoticia() {
		return receberNoticia;
	}

	public void setReceberNoticia(boolean receberNoticia) {
		this.receberNoticia = receberNoticia;
	}

	public boolean isUltimasAtualizacoes() {
		return ultimasAtualizacoes;
	}

	public void setUltimasAtualizacoes(boolean ultimasAtualizacoes) {
		this.ultimasAtualizacoes = ultimasAtualizacoes;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

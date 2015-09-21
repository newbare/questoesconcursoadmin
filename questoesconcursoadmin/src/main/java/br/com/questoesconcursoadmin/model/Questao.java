package br.com.questoesconcursoadmin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "QUE_QUESTAO", schema = "dbo")
public class Questao implements Serializable, BaseDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2987783611929726744L;

	@Id
	@SequenceGenerator(name = "questao_sequence", sequenceName = "dbo.questao_sequence", allocationSize = 1, initialValue = 2)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questao_sequence")
	@Column(name = "que_id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "que_questao")
	private String questao;
	
	@Column(name = "que_numero")
	private Integer numero;
	
	@Column(name = "for_id")
	private Long formaInclusao;
	
	@ManyToOne
	@JoinColumn(name = "cat_id")
	private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "tex_id")
	private TextoDescritivo textoDescritivo;
	
	@Column(name = "tip_id")
	private Long tipoQuestao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "que_data_cadastro")
	private Date dataCadastro;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "que_data_alteracao")
	private Date dataAlteracao;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="que_id", nullable=true)
	private List<QuestaoProva> questaoProva;
	
	@Transient
	private Alternativa alternativaGabarito;
	
	@Transient
	private List<Alternativa> listaAlternativa;
	
	//Palavra Chave é um atributo usado nas pesquisas.
	@Transient
	private String palavraChave;
	
	@Transient
	private Prova prova;
	
	@Transient
	private Prova[] provas;
	
	//Variável setada pra true se a resposta dada para a questão for verdade e false caso seja errada.
	@Transient
	private Boolean resposta = false;
	
	@Override
	public String toString() {
		return this.questao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestao() {
		return questao;
	}

	public void setQuestao(String questao) {
		this.questao = questao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Long getFormaInclusao() {
		return formaInclusao;
	}

	public void setFormaInclusao(Long formaInclusao) {
		this.formaInclusao = formaInclusao;
	}

	public Categoria getCategoria() {
		if(this.categoria == null){
			this.categoria = new Categoria();
		}
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public TextoDescritivo getTextoDescritivo() {
		if(this.textoDescritivo == null){
			this.textoDescritivo = new TextoDescritivo();
		}
		return textoDescritivo;
	}

	public void setTextoDescritivo(TextoDescritivo textoDescritivo) {
		this.textoDescritivo = textoDescritivo;
	}

	public Long getTipoQuestao() {
		return tipoQuestao;
	}

	public void setTipoQuestao(Long tipoQuestao) {
		this.tipoQuestao = tipoQuestao;
	}

	public List<Alternativa> getListaAlternativa() {
		if(listaAlternativa == null){
			listaAlternativa = new ArrayList<Alternativa>();
		}
		return listaAlternativa;
	}

	public void setListaAlternativa(List<Alternativa> listaAlternativa) {
		this.listaAlternativa = listaAlternativa;
	}
	
	public Alternativa getAlternativaGabarito() {
		if(alternativaGabarito == null){
			alternativaGabarito = new Alternativa();
		}
		return alternativaGabarito;
	}

	public void setAlternativaGabarito(Alternativa alternativaGabarito) {
		this.alternativaGabarito = alternativaGabarito;
	}

	public String getPalavraChave() {
		return palavraChave;
	}

	public void setPalavraChave(String palavraChave) {
		this.palavraChave = palavraChave;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Prova[] getProvas() {
		return provas;
	}

	public void setProvas(Prova[] provas) {
		this.provas = provas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((alternativaGabarito == null) ? 0 : alternativaGabarito
						.hashCode());
		result = prime * result
				+ ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result
				+ ((dataAlteracao == null) ? 0 : dataAlteracao.hashCode());
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result
				+ ((formaInclusao == null) ? 0 : formaInclusao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((listaAlternativa == null) ? 0 : listaAlternativa.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result
				+ ((palavraChave == null) ? 0 : palavraChave.hashCode());
		result = prime * result + Arrays.hashCode(provas);
		result = prime * result + ((questao == null) ? 0 : questao.hashCode());
		result = prime * result
				+ ((textoDescritivo == null) ? 0 : textoDescritivo.hashCode());
		result = prime * result
				+ ((tipoQuestao == null) ? 0 : tipoQuestao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questao other = (Questao) obj;
		if (alternativaGabarito == null) {
			if (other.alternativaGabarito != null)
				return false;
		} else if (!alternativaGabarito.equals(other.alternativaGabarito))
			return false;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (dataAlteracao == null) {
			if (other.dataAlteracao != null)
				return false;
		} else if (!dataAlteracao.equals(other.dataAlteracao))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (formaInclusao == null) {
			if (other.formaInclusao != null)
				return false;
		} else if (!formaInclusao.equals(other.formaInclusao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaAlternativa == null) {
			if (other.listaAlternativa != null)
				return false;
		} else if (!listaAlternativa.equals(other.listaAlternativa))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (palavraChave == null) {
			if (other.palavraChave != null)
				return false;
		} else if (!palavraChave.equals(other.palavraChave))
			return false;
		if (!Arrays.equals(provas, other.provas))
			return false;
		if (questao == null) {
			if (other.questao != null)
				return false;
		} else if (!questao.equals(other.questao))
			return false;
		if (textoDescritivo == null) {
			if (other.textoDescritivo != null)
				return false;
		} else if (!textoDescritivo.equals(other.textoDescritivo))
			return false;
		if (tipoQuestao == null) {
			if (other.tipoQuestao != null)
				return false;
		} else if (!tipoQuestao.equals(other.tipoQuestao))
			return false;
		return true;
	}

	public Boolean getResposta() {
		return resposta;
	}

	public void setResposta(Boolean resposta) {
		this.resposta = resposta;
	}

	public List<QuestaoProva> getQuestaoProva() {
		return questaoProva;
	}

	public void setQuestaoProva(List<QuestaoProva> questaoProva) {
		this.questaoProva = questaoProva;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

}

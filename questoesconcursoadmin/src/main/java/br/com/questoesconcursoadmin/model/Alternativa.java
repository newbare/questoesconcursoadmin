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
@Table(name="QUE_ALTERNATIVA", schema = "dbo")
public class Alternativa implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5542130221515683168L;

	@Id
	@SequenceGenerator(name="alternativa_sequence",sequenceName="dbo.alternativa_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="alternativa_sequence")
	@Column(name = "alt_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "alt_alternativa")
	private String alternativa;
	
	@ManyToOne
	@JoinColumn(name = "que_id")
	private Questao questao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(String alternativa) {
		this.alternativa = alternativa;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}


}

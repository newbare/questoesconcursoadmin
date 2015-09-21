package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="QUE_TIPO_QUESTAO", schema = "dbo")
public class TipoQuestao implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731794319689221691L;
	
	@Id
	@SequenceGenerator(name="tipo_questao_sequence",sequenceName="dbo.tipo_questao_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tipo_questao_sequence")
	@Column(name = "tip_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "tip_descricao")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

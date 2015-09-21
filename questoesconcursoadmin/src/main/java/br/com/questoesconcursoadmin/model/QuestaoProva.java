package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="QUE_QUESTAO_PROVA", schema = "dbo")
@IdClass(value=QuestaoProvaPK.class)
public class QuestaoProva implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -879544099436767959L;

	@Id
	@Column(name="que_id")
	private Long idQuestao = null;
	
	@Id
	@Column(name="pro_id")
	private Integer idProva = null;

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public Integer getIdProva() {
		return idProva;
	}

	public void setIdProva(Integer idProva) {
		this.idProva = idProva;
	}

}

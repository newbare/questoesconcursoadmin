package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="QUE_QUESTAO_COMENTARIO", schema = "dbo")
@IdClass(value=QuestaoComentarioPK.class)
public class QuestaoComentario implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6196285520547159383L;

	@Id
	@Column(name="que_id")
	private Integer idQuestao = null;
	
	@Id
	@Column(name="com_id")
	private Integer idComentario = null;

	public Integer getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Integer idQuestao) {
		this.idQuestao = idQuestao;
	}

	public Integer getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Integer idComentario) {
		this.idComentario = idComentario;
	}

}

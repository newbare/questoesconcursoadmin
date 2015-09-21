package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="QUE_GABARITO", schema = "dbo")
@IdClass(value=GabaritoPK.class)
public class Gabarito implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364580974600078592L;

	@Id
	@Column(name="que_id")
	private Long idQuestao = null;
	
	@Id
	@Column(name="alt_id")
	private Long idAlternativa = null;

	public Long getIdQuestao() {
		return idQuestao;
	}

	public void setIdQuestao(Long idQuestao) {
		this.idQuestao = idQuestao;
	}

	public Long getIdAlternativa() {
		return idAlternativa;
	}

	public void setIdAlternativa(Long idAlternativa) {
		this.idAlternativa = idAlternativa;
	}

}

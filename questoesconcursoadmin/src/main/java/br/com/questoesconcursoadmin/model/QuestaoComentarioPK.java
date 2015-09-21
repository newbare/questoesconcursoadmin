package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

public class QuestaoComentarioPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6815172981097390773L;

	private Integer idQuestao = null;
	
	private Integer idComentario = null;
	
	public QuestaoComentarioPK(){
		
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idComentario == null) ? 0 : idComentario.hashCode());
		result = prime * result
				+ ((idQuestao == null) ? 0 : idQuestao.hashCode());
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
		QuestaoComentarioPK other = (QuestaoComentarioPK) obj;
		if (idComentario == null) {
			if (other.idComentario != null)
				return false;
		} else if (!idComentario.equals(other.idComentario))
			return false;
		if (idQuestao == null) {
			if (other.idQuestao != null)
				return false;
		} else if (!idQuestao.equals(other.idQuestao))
			return false;
		return true;
	}

		
}

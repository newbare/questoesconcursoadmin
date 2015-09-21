package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

public class QuestaoProvaPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7224551864696608055L;

	private Long idQuestao = null;
	
	private Integer idProva = null;
	
	public QuestaoProvaPK(){
		
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProva == null) ? 0 : idProva.hashCode());
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
		QuestaoProvaPK other = (QuestaoProvaPK) obj;
		if (idProva == null) {
			if (other.idProva != null)
				return false;
		} else if (!idProva.equals(other.idProva))
			return false;
		if (idQuestao == null) {
			if (other.idQuestao != null)
				return false;
		} else if (!idQuestao.equals(other.idQuestao))
			return false;
		return true;
	}

}

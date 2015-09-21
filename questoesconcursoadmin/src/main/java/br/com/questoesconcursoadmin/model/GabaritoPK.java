package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

public class GabaritoPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -595282717623524893L;

	private Long idQuestao = null;
	
	private Long idAlternativa = null;
	
	public GabaritoPK(){
		
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idAlternativa == null) ? 0 : idAlternativa.hashCode());
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
		GabaritoPK other = (GabaritoPK) obj;
		if (idAlternativa == null) {
			if (other.idAlternativa != null)
				return false;
		} else if (!idAlternativa.equals(other.idAlternativa))
			return false;
		if (idQuestao == null) {
			if (other.idQuestao != null)
				return false;
		} else if (!idQuestao.equals(other.idQuestao))
			return false;
		return true;
	}
		
}

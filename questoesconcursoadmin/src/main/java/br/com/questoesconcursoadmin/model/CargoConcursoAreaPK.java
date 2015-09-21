package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

public class CargoConcursoAreaPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8373219867790603236L;
	
	private Integer id;

	private Integer idCargo = null;
	
	private Long idConcurso = null;
	
	private Integer idArea = null;
	
	public CargoConcursoAreaPK(){
		
	}

	public Integer getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Integer idCargo) {
		this.idCargo = idCargo;
	}

	public Long getIdConcurso() {
		return idConcurso;
	}

	public void setIdConcurso(Long idConcurso) {
		this.idConcurso = idConcurso;
	}

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idArea == null) ? 0 : idArea.hashCode());
		result = prime * result + ((idCargo == null) ? 0 : idCargo.hashCode());
		result = prime * result
				+ ((idConcurso == null) ? 0 : idConcurso.hashCode());
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
		CargoConcursoAreaPK other = (CargoConcursoAreaPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idArea == null) {
			if (other.idArea != null)
				return false;
		} else if (!idArea.equals(other.idArea))
			return false;
		if (idCargo == null) {
			if (other.idCargo != null)
				return false;
		} else if (!idCargo.equals(other.idCargo))
			return false;
		if (idConcurso == null) {
			if (other.idConcurso != null)
				return false;
		} else if (!idConcurso.equals(other.idConcurso))
			return false;
		return true;
	}

}

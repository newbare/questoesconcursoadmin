package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

public class UsuarioPerfilPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6102877134533429343L;

	private Integer IdUsuario = null;
	
	private Integer IdPerfil = null;
	
	public UsuarioPerfilPK(){
		
	}

	public Integer getIdUsuario() {
		return IdUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		IdUsuario = idUsuario;
	}

	public Integer getIdPerfil() {
		return IdPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		IdPerfil = idPerfil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((IdPerfil == null) ? 0 : IdPerfil.hashCode());
		result = prime * result
				+ ((IdUsuario == null) ? 0 : IdUsuario.hashCode());
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
		UsuarioPerfilPK other = (UsuarioPerfilPK) obj;
		if (IdPerfil == null) {
			if (other.IdPerfil != null)
				return false;
		} else if (!IdPerfil.equals(other.IdPerfil))
			return false;
		if (IdUsuario == null) {
			if (other.IdUsuario != null)
				return false;
		} else if (!IdUsuario.equals(other.IdUsuario))
			return false;
		return true;
	}
	
	
}

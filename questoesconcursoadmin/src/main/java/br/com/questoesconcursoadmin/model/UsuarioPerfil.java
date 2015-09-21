package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="SEG_USUARIO_PERFIL", schema = "dbo")
@IdClass(value=UsuarioPerfilPK.class)
public class UsuarioPerfil implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6488662073090746219L;
	
	@Id
	@Column(name="usu_id")
	private Integer IdUsuario = null;
	
	@Id
	@Column(name="per_id")
	private Integer IdPerfil = null;

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

}

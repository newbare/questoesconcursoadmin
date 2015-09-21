package br.com.questoesconcursoadmin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="QUE_COMENTARIO", schema = "dbo")
public class Comentario implements Serializable, BaseDomain{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="comentario_sequence",sequenceName="dbo.comentario_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="comentario_sequence")
	@Column(name = "com_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "com_comentario")
	private String comentario;
	
	@Column(name="com_data")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "usu_id")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="QUE_TEXTO_DESCRITIVO", schema = "dbo")
public class TextoDescritivo implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731794319689221691L;
	
	@Id
	@SequenceGenerator(name="texto_descritivo_sequence",sequenceName="dbo.texto_descritivo_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="texto_descritivo_sequence")
	@Column(name = "tex_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "tex_texto")
	private String texto;
	
	@Column(name = "tex_referencia")
	private String referencia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

}

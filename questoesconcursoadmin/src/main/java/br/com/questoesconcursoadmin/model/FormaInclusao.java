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
@Table(name="QUE_FORMA_INCLUSAO", schema = "dbo")
public class FormaInclusao implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731794319689221691L;
	
	@Id
	@SequenceGenerator(name="forma_inclusao_sequence",sequenceName="dbo.forma_inclusao_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="forma_inclusao_sequence")
	@Column(name = "for_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "for_nome")
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

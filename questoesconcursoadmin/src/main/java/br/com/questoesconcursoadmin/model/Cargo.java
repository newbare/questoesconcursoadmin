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
@Table(name="QUE_CARGO", schema = "dbo")
public class Cargo implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7584259474673809240L;

	@Id
	@SequenceGenerator(name="cargo_sequence",sequenceName="dbo.cargo_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cargo_sequence")
	@Column(name = "car_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "car_nome")
	private String nome;
	
	@Override
	public String toString() {
		return this.nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

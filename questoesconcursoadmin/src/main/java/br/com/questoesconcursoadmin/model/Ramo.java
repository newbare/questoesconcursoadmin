package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

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
@Table(name="QUE_RAMO", schema = "dbo")
public class Ramo implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 703208418746667716L;

	@Id
	@SequenceGenerator(name="ramo_sequence",sequenceName="dbo.ramo_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ramo_sequence")
	@Column(name = "ram_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "ram_ramo")
	private String ramo;
	
	@ManyToOne
	@JoinColumn(name="esp_id")
	private Especialidade especialidade;
	
	@Override
	public String toString() {
		return this.ramo;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public Especialidade getEspecialidade() {
		if(especialidade == null){
			especialidade = new Especialidade();
		}
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

}

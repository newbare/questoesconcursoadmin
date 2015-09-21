package br.com.questoesconcursoadmin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QUE_CARGO_CONCURSO_AREA", schema = "dbo")
@IdClass(value=CargoConcursoAreaPK.class)
public class CargoConcursoArea implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5145220235184462819L;

	@Id
	@SequenceGenerator(name="cargo_concurso_area_sequence",sequenceName="dbo.cargo_concurso_area_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cargo_concurso_area_sequence")
	@Column(name = "cca_id", unique=true, nullable=false)
	private Integer id;

	@Id
	@Column(name="car_id")
	private Integer idCargo = null;
	
	@Id
	@Column(name="con_id")
	private Long idConcurso = null;
	
	@Id
	@Column(name="are_id")
	private Integer idArea = null;
	
	@ManyToOne
	@JoinColumn(name = "esp_id")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumn(name = "ram_id")
	private Ramo ramo;
	
	@Transient
	private Area area;
	
	@Transient
	private Cargo cargo;
	
	@Transient
	private Concurso concurso;

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

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ramo getRamo() {
		return ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

}

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
@Table(name="QUE_AREA", schema = "dbo")
public class Area implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8065157758689425733L;

	@Id
	@SequenceGenerator(name="area_sequence",sequenceName="dbo.area_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="area_sequence")
	@Column(name = "are_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "are_area")
	private String area;
	
	@Override
	public String toString() {
		return this.area;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}

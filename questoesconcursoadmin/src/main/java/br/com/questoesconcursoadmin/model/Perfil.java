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
@Table(name="SEG_PERFIL", schema = "dbo")
public class Perfil implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731794319689221691L;
	
	@Id
	@SequenceGenerator(name="perfil_sequence",sequenceName="dbo.perfil_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="perfil_sequence")
	@Column(name = "per_id",unique=true,nullable=false)
	private Integer id;
	
	@Column(name = "seg_authority")
	private String authority;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public String toString() {
		return this.authority;
	}

}

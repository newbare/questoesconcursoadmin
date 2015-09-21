package br.com.questoesconcursoadmin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QUE_CONCURSO", schema = "dbo")
public class Concurso implements Serializable, BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8555502012672065013L;

	@Id
	@SequenceGenerator(name="concurso_sequence",sequenceName="dbo.concurso_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="concurso_sequence")
	@Column(name = "con_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "con_ano")
	private String ano;
	
	@ManyToOne
	@JoinColumn(name="ban_id")
	private Banca banca;
	
	@ManyToOne
	@JoinColumn(name="org_id")
	private Orgao orgao;
	
	@Column(name = "con_concluido",columnDefinition = "BOOLEAN")
	private Boolean concluido;
	
	@Transient
	private List<CargoConcursoArea> listaCargoConcursoArea;
	
	@Transient
	private CargoConcursoArea cargoConcursoArea;
	
	@Override
	public String toString() {
		return this.orgao.getOrgao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Banca getBanca() {
		return banca;
	}

	public void setBanca(Banca banca) {
		this.banca = banca;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	public List<CargoConcursoArea> getListaCargoConcursoArea() {
		if(listaCargoConcursoArea == null){
			listaCargoConcursoArea = new ArrayList<CargoConcursoArea>();
		}
		return listaCargoConcursoArea;
	}

	public void setListaCargoConcursoArea(
			List<CargoConcursoArea> listaCargoConcursoArea) {
		this.listaCargoConcursoArea = listaCargoConcursoArea;
	}

	public CargoConcursoArea getCargoConcursoArea() {
		if(this.cargoConcursoArea == null){
			this.cargoConcursoArea = new CargoConcursoArea();
		}
		return cargoConcursoArea;
	}

	public void setCargoConcursoArea(CargoConcursoArea cargoConcursoArea) {
		this.cargoConcursoArea = cargoConcursoArea;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ano == null) ? 0 : ano.hashCode());
		result = prime * result + ((banca == null) ? 0 : banca.hashCode());
		result = prime
				* result
				+ ((cargoConcursoArea == null) ? 0 : cargoConcursoArea
						.hashCode());
		result = prime * result
				+ ((concluido == null) ? 0 : concluido.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime
				* result
				+ ((listaCargoConcursoArea == null) ? 0
						: listaCargoConcursoArea.hashCode());
		result = prime * result + ((orgao == null) ? 0 : orgao.hashCode());
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
		Concurso other = (Concurso) obj;
		if (ano == null) {
			if (other.ano != null)
				return false;
		} else if (!ano.equals(other.ano))
			return false;
		if (banca == null) {
			if (other.banca != null)
				return false;
		} else if (!banca.equals(other.banca))
			return false;
		if (cargoConcursoArea == null) {
			if (other.cargoConcursoArea != null)
				return false;
		} else if (!cargoConcursoArea.equals(other.cargoConcursoArea))
			return false;
		if (concluido == null) {
			if (other.concluido != null)
				return false;
		} else if (!concluido.equals(other.concluido))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listaCargoConcursoArea == null) {
			if (other.listaCargoConcursoArea != null)
				return false;
		} else if (!listaCargoConcursoArea.equals(other.listaCargoConcursoArea))
			return false;
		if (orgao == null) {
			if (other.orgao != null)
				return false;
		} else if (!orgao.equals(other.orgao))
			return false;
		return true;
	}

}

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
@Table(name="QUE_ORGAO_INSTITUICAO", schema = "dbo")
public class Orgao implements Serializable, BaseDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8555502012672065013L;

	@Id
	@SequenceGenerator(name="orgao_instituicao_sequence",sequenceName="dbo.orgao_instituicao_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="orgao_instituicao_sequence")
	@Column(name = "org_id", unique=true, nullable=false)
	private Long id;
	
	@Column(name = "org_orgao")
	private String orgao;
	
	@Column(name = "org_orgao_abreviacao")
	private String orgaoAbreviacao;
	
	@Override
	public String toString() {
		return this.orgao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrgao() {
		return orgao;
	}

	public void setOrgao(String orgao) {
		this.orgao = orgao;
	}

	public String getOrgaoAbreviacao() {
		return orgaoAbreviacao;
	}

	public void setOrgaoAbreviacao(String orgaoAbreviacao) {
		this.orgaoAbreviacao = orgaoAbreviacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((orgao == null) ? 0 : orgao.hashCode());
		result = prime * result
				+ ((orgaoAbreviacao == null) ? 0 : orgaoAbreviacao.hashCode());
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
		Orgao other = (Orgao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (orgao == null) {
			if (other.orgao != null)
				return false;
		} else if (!orgao.equals(other.orgao))
			return false;
		if (orgaoAbreviacao == null) {
			if (other.orgaoAbreviacao != null)
				return false;
		} else if (!orgaoAbreviacao.equals(other.orgaoAbreviacao))
			return false;
		return true;
	}

}

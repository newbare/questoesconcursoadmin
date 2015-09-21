package br.com.questoesconcursoadmin.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="QUE_PROVA", schema = "dbo")
public class Prova implements Serializable, BaseDomain{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8687640664843694861L;

	@Id
	@SequenceGenerator(name="prova_sequence",sequenceName="dbo.prova_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="prova_sequence")
	@Column(name = "pro_id", unique=true, nullable=false)
	private Integer id;
	
	@Column(name = "pro_data")
	private Date data;
	
	@Column(name="cca_id")
	private Integer cargoConcursoArea;
	
	@Column(name = "pro_concluido")
	private Boolean concluida;
	
	@Transient
	private CargoConcursoArea cca;
	
	@Override
	public String toString() {
		StringBuilder retorno = new StringBuilder();
		if(cca.getCargo().getNome() != null){
			retorno.append(cca.getCargo());
			if(cca.getArea() != null){
				retorno.append(" - ").append(cca.getArea());
			}
			if(cca.getEspecialidade() != null){
				retorno.append(" - ").append(cca.getEspecialidade());
			}
			if(cca.getRamo() != null){
				retorno.append(" - ").append(cca.getRamo());
			}
		}
		return retorno.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getCargoConcursoArea() {
		return cargoConcursoArea;
	}

	public void setCargoConcursoArea(Integer cargoConcursoArea) {
		this.cargoConcursoArea = cargoConcursoArea;
	}

	public CargoConcursoArea getCca() {
		return cca;
	}

	public void setCca(CargoConcursoArea cca) {
		this.cca = cca;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cargoConcursoArea == null) ? 0 : cargoConcursoArea
						.hashCode());
		result = prime * result + ((cca == null) ? 0 : cca.hashCode());
		result = prime * result
				+ ((concluida == null) ? 0 : concluida.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Prova other = (Prova) obj;
		if (cargoConcursoArea == null) {
			if (other.cargoConcursoArea != null)
				return false;
		} else if (!cargoConcursoArea.equals(other.cargoConcursoArea))
			return false;
		if (cca == null) {
			if (other.cca != null)
				return false;
		} else if (!cca.equals(other.cca))
			return false;
		if (concluida == null) {
			if (other.concluida != null)
				return false;
		} else if (!concluida.equals(other.concluida))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

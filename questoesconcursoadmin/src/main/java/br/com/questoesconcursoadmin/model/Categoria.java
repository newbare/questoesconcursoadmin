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
@Table(name = "QUE_CATEGORIA", schema = "dbo")
public class Categoria implements Serializable, BaseDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7731794319689221691L;

	@Id
	@SequenceGenerator(name = "categoria_sequence", sequenceName = "dbo.categoria_sequence", allocationSize = 1, initialValue = 2)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_sequence")
	@Column(name = "cat_id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "cat_nome")
	private String nome;

	@ManyToOne
	@JoinColumn(name = "dis_id")
	private Disciplina disciplina;

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

	public String toString() {
		return this.nome;
	}

	public Disciplina getDisciplina() {
		if(this.disciplina == null){
			this.disciplina = new Disciplina();
		}
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((disciplina == null) ? 0 : disciplina.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
		if (disciplina == null) {
			if (other.disciplina != null)
				return false;
		} else if (!disciplina.equals(other.disciplina))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}

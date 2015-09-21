package br.com.questoesconcursoadmin.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="SEG_USUARIO", schema = "dbo")
public class Usuario implements Serializable, BaseDomain{

	private static final long serialVersionUID = 6792204874630321625L;
	
	@Id
	@SequenceGenerator(name="usuario_sequence",sequenceName="dbo.usuario_sequence",allocationSize=1,initialValue=2)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="usuario_sequence")
	@Column(name = "usu_id",unique=true,nullable=false)
	private Integer id;
	
	@Column(name = "seg_password")
	private String senha;
	
	@Column(name = "seg_email")
	private String email;

	@Column(name = "seg_enable",columnDefinition = "BOOLEAN")
	private boolean enable;
	
	@Transient
	private List<String> perfis;
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}

}

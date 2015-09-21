package br.com.questoesconcursoadmin.model;

public class Candidato implements Comparable<Candidato>{

	private String inscricao;
	private String nome;
	private Double nota;
	private Double notaRedacao;
	private Boolean aprovado;
	
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public Double getNotaRedacao() {
		return notaRedacao;
	}
	public void setNotaRedacao(Double notaRedacao) {
		this.notaRedacao = notaRedacao;
	}
	public Boolean getAprovado() {
		return aprovado;
	}
	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}
	
	public int compareTo(Candidato outraEntidade) {
		if(outraEntidade.getNotaRedacao() != null && this.notaRedacao != null){
	        if (this.nota + this.notaRedacao < outraEntidade.getNota() + outraEntidade.getNotaRedacao()) {
	            return 1;
	        }else if(this.nota + this.notaRedacao > outraEntidade.getNota() + outraEntidade.getNotaRedacao()) {
	            return -1;
	        }
		}else{
			if (this.nota < outraEntidade.getNota()) {
	            return 1;
	        }else if(this.nota > outraEntidade.getNota()) {
	            return -1;
	        }
		}
        return 0;
    }
	
	
}

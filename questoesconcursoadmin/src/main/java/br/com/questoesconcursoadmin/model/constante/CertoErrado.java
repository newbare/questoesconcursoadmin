package br.com.questoesconcursoadmin.model.constante;

public enum CertoErrado {
	
CERTO(1L, "Certo") , ERRADO(2L, "Errado"), ANULADA(3L, "Anulada");
	
	private Long codigo;
	private String descricao;

	private CertoErrado(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

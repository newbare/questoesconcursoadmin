package br.com.questoesconcursoadmin.model.constante;

public enum FormaInclusao {
	
DIGITACAO(1L, "Digitacao") , OCR(2L, "OCR");
	
	private Long codigo;
	private String descricao;

	private FormaInclusao(Long codigo, String descricao) {
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

package br.com.questoesconcursoadmin.model.constante;

public enum TipoQuestao {
	
CERTO_ERRADO(1L, "Certo ou Errado") , MULTIPLA_ESCOLHA(2L, "Multipla Escolha");
	
	private Long codigo;
	private String descricao;

	private TipoQuestao(Long codigo, String descricao) {
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

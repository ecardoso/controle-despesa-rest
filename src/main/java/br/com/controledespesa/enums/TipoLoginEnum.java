package br.com.controledespesa.enums;

public enum TipoLoginEnum {

	SISTEMA(1),
	GOOGLE(2);

	private Integer chave;

	private TipoLoginEnum(Integer chave) {
		this.chave = chave;
	}

	public Integer getChave() {
		return chave;
	}

}

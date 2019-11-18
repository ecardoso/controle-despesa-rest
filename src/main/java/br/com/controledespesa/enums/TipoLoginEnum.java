package br.com.controledespesa.enums;

public enum TipoLoginEnum {

	SISTEMA(1),
	GOOGLE(2);

	private Integer chave;

	private TipoLoginEnum(Integer chave) {
		this.setChave(chave);
	}

	public Integer getChave() {
		return chave;
	}

	private void setChave(Integer chave) {
		this.chave = chave;
	}

}

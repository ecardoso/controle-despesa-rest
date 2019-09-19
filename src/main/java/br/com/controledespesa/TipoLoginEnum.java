package br.com.controledespesa;

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

	public void setChave(Integer chave) {
		this.chave = chave;
	}

}

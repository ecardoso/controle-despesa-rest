package br.com.controledespesa.exception;

public class DadosException extends Exception {

	private static final long serialVersionUID = 1L;

	public DadosException() {

	}

	public DadosException(String message) {
		super(message);
	}

	public DadosException(Throwable cause) {
		super(cause);
	}

	public DadosException(String message, Throwable cause) {
		super(message, cause);
	}

}

package com.rafaelAbreu.JogoQuiz.exceptions;

public class UsuarioExistenteException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioExistenteException(String message) {
        super(message);
    }
}

package br.edu.fatec.projectsmartrow.exceptions;

public class ExcessaoConexaoDB extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcessaoConexaoDB(String msg) {
		System.out.println(msg);
	}
}

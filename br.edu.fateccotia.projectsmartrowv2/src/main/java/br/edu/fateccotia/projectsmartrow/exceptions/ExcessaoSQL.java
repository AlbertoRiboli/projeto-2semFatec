package br.edu.fateccotia.projectsmartrow.exceptions;

public class ExcessaoSQL extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ExcessaoSQL(String msg) {
		super(msg);
	}
}

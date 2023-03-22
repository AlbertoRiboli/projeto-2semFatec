package br.edu.fatec.projectsmartrow.model;

public class Faturamento {
	
	private String padrao;
	private Double taxa;
	
	public Faturamento () {}
	
	public Faturamento(String padrao, Double taxa) {
		this.padrao = padrao;
		this.taxa = taxa;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	
	
	
	

}

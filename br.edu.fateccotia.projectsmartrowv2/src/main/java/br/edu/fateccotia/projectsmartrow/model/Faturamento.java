package br.edu.fateccotia.projectsmartrow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Faturamento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
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

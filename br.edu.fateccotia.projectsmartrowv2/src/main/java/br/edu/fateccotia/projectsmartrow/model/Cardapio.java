package br.edu.fateccotia.projectsmartrow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Cardapio { // Classe que armazena uma lista de bebidas e uma lista de pratos que compoem o
						// Cardapio criado pelo estabelecimento
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDCardapio;
	@OneToMany
	@JoinColumn(name = "idcardapio")
	private List<Pratos> pratos = new ArrayList<>();
	@OneToMany
	@JoinColumn(name = "idcardapio")
	private List<Bebidas> bebidas = new ArrayList<>();

	public Cardapio() {
	}

	public Cardapio(Integer IDCardapio, List<Pratos> pratos, List<Bebidas> bebidas) {
		this.IDCardapio = IDCardapio;
		this.pratos = pratos;
		this.bebidas = bebidas;
	}

	public Integer getIDCardapio() {
		return IDCardapio;
	}

	public void setIDCardapio(Integer iDCardapio) {
		this.IDCardapio = iDCardapio;
	}

	public List<Bebidas> getBebidas() {
		return bebidas;
	}

	public void setListBebidas(List<Bebidas> bebidas) {
		this.bebidas = bebidas;
	}

	public void setListPratos(List<Pratos> pratos) {
		this.pratos = pratos;
	}

	public void setBebidas(Bebidas bebidas) {
		this.bebidas.add(bebidas);
	}

	public void setPratos(Pratos pratos) {
		this.pratos.add(pratos);
	}

	public List<Pratos> getPratos() {
		return pratos;
	}


	
	

}

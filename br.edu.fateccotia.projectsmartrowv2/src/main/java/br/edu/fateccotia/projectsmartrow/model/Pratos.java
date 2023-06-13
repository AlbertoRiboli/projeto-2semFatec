package br.edu.fateccotia.projectsmartrow.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pratos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDPrato;
	private String nome;
	private String tipoPrato;
	private String ingredientes;
	private Double valor;
	private String imagem;
//	private List<Double> avaliacao = new ArrayList<>();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idcardapio", updatable = false, insertable = false)
	private Cardapio cardapio;
	
	public Pratos() {
	}

	public Pratos(String nome, String tipoPrato, String ingredientes, Double valor, String imagem
			) {

		this.nome = nome;
		this.tipoPrato = tipoPrato;
		this.ingredientes = ingredientes;
		this.valor = valor;
		this.imagem = imagem;
//		this.avaliacao.add(5.00);

	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Integer getIDPrato() {
		return IDPrato;
	}

	public void setIDPrato(Integer iDPrato) {
		IDPrato = iDPrato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPrato() {
		return tipoPrato;
	}

	public void setTipoPrato(String tipoPrato) {
		this.tipoPrato = tipoPrato;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

//	public Double getAvaliacao() {
//		Double mediaAvaliacao = 0.0;
//		for (Double obj : avaliacao) {
//			mediaAvaliacao += obj;
//		}
//		mediaAvaliacao = mediaAvaliacao / (avaliacao.size());
//		return mediaAvaliacao;
//
//	}
//
//	public void setAvaliacao(Double avaliacao) {
//		this.avaliacao.add(avaliacao);
//	}

	public void imprimirPratos(List<Pratos> pratos) {
		if (pratos == null) {
			System.out.println("A lista de pratos esta vazia!");
		} else {
			System.out.println("--------------------------------");
			System.out.println("PRATOS DO CARDAPIO");
			System.out.println("--------------------------------");
			for (Pratos obj : pratos) {
				System.out.println(obj);
			}
			System.out.println("--------------------------------");
		}
	}

	@Override
	public String toString() {
		return "Identificador do Prato: " + IDPrato 
				+ "\nNome: " + nome 
				+ "\nTipo de Prato: " + tipoPrato 
				+ "\nIngredientes: " + ingredientes
				+ "\nValor: R$ " + String.format("%.2f", valor);
	}


}

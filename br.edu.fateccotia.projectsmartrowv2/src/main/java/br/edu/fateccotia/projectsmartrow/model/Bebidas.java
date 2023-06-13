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
public class Bebidas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDBebida;
	private String nome;
	private String tipoBebida;
	private Double valor;
	private String imagem;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idcardapio", updatable = false, insertable = false)
	private Cardapio cardapio;

	public Bebidas() {					//Classe Bebidas que compoe o Cardapio de Cada estabelecimento
	}

	public Bebidas(String nome, String tipoBebida, Double valor, String imagem) {
		this.nome = nome;
		this.tipoBebida = tipoBebida;
		this.valor = valor;
		this.imagem = imagem;
	}

	public Integer getIDBebida() {
		return IDBebida;
	}

	public void setIDBebida(Integer iDBebida) {
		IDBebida = iDBebida;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoBebida() {
		return tipoBebida;
	}

	public void setTipoBebida(String tipoBebida) {
		this.tipoBebida = tipoBebida;
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


	@Override
	public String toString() {
		return "Identificador da Bebida: " + IDBebida 
				+ "\nNome: " + nome 
				+ "\nTipo de Bebida: " + tipoBebida 
				+ "\nValor: R$ " + String.format("%.2f", valor);
	}


	
}

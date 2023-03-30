package br.edu.fatec.projectsmartrow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;

public class Cardapio {					//Classe que armazena uma lista de bebidas e uma lista de pratos que compoem o Cardapio criado pelo estabelecimento

	private Integer IDCardapio;
	private List<Pratos> pratos;
	private List<Bebidas> bebidas;

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

	public void setBebidas(List<Bebidas> bebidas) {
		this.bebidas = bebidas;
	}

	public void setPratos(List<Pratos> pratos) {
		this.pratos = pratos;
	}

	public List<Pratos> getPratos() {
		return pratos;
	}

	public Cardapio adicionarCardapio() {		//Metodo que adiciona uma composição de uma lista de pratos e bebidas que compoem um Cardapio

		Scanner sc = new Scanner(System.in);
		Pratos pt = new Pratos();
		Bebidas bd = new Bebidas();
		List<Pratos> pratos = pt.adicionarPrato();

		System.out.println("\n\n--------------------------------");
		System.out.println("Deseja Cadastrar bebidas? 1-SIM | 2=NAO: ");
		System.out.println("--------------------------------");
		int opc = 0;

		while (opc != 2) {
			opc = sc.nextInt();
			if (opc == 1) {
				List<Bebidas> bebidas = bd.adicionarBebida();
				opc = 2;
			} else if (opc == 2) {
				System.out.println("Ok! Voce podera adicionar seu cardapio \nposteriormente nas opcoes do aplicativo");
				opc = 2;
			} else {
				System.out.print("Opção Inválida! Digite novamente: ");
			}
		}

		Cardapio cd = new Cardapio(null, pratos, bebidas);
		return cd;
	}

	public void imprimirCardapio() {					//Metodo para imprimir um cardapio que seja passado como argumento na chamada da função
		if (pratos == null && bebidas == null) {
			System.out.println("O Cadastro de Cardapio está vazio!");
		} else {
			Pratos metodopratos = new Pratos();
			Bebidas metodobebidas = new Bebidas();
			if (pratos != null) {
				metodopratos.imprimirPratos(pratos);
			} else {
				System.out.println("Lista de Pratos está Vazia!");
			}
			if (bebidas != null) {
				metodobebidas.imprimirBebidas(bebidas);
			} else {
				System.out.println("Lista de Bebidas está vazia!");
			}
		}

	}

}

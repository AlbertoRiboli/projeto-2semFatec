package br.edu.fatec.projectsmartrow.model;

import java.util.List;
import java.util.Scanner;

public class Cardapio {

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

	public Cardapio adicionarCardapio() {

		Scanner sc = new Scanner(System.in);
		Pratos pt = new Pratos();
		Bebidas bd = new Bebidas();
		List<Pratos> pratos = pt.adicionarPrato();
		System.out.println("\n\n--------------------------------");
		System.out.print("Deseja Cadastrar bebidas? 1-SIM | 2=NAO: ");
		System.out.println("--------------------------------\n\n");
		int opc = 0;
		while (opc != 2) {
			opc = sc.nextInt();
			if (opc == 1) {
				List<Bebidas> bebidas = bd.adicionarBebida();
			} else if (opc == 2) {
				System.out.println("Ok! Voce podera adicionar seu cardapio \nposteriormente nas opcoes do aplicativo");
			} else {
				System.out.print("Opção Inválida! Digite novamente: ");
			}
		}

		Cardapio cd = new Cardapio(null, pratos, bebidas);
		return cd;
	}

	public void imprimirCardapio() {
		if (pratos == null && bebidas == null) {
			System.out.println("O Cadastro de Cardapio está vazio!");
		} else {
			Pratos metodopratos = new Pratos();
			Bebidas metodobebidas = new Bebidas();
			metodopratos.imprimirPratos(pratos);
			metodobebidas.imprimirBebidas(bebidas);
		}
		
	}
	
	public List<Bebidas> getBebidas() {
		return bebidas;
	}
	
	public List<Pratos> getPratos() {
		return pratos;
	}
}
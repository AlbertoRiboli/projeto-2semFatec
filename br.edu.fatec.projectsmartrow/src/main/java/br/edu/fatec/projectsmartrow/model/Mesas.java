package br.edu.fatec.projectsmartrow.model;

import java.util.List;
import java.util.Scanner;

public class Mesas {
	private Integer IDMesa;
	private Integer numMesa;
	private Integer capacidadePessoas;

	public Mesas () {}
	
	

	public Mesas(Integer numMesa, Integer capacidadePessoas) {
		this.numMesa = numMesa;
		this.capacidadePessoas = capacidadePessoas;
	}



	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public Integer getCapacidadePessoas() {
		return capacidadePessoas;
	}

	public void setCapacidadePessoas(Integer capacidadePessoas) {
		this.capacidadePessoas = capacidadePessoas;
	}

	@Override
	public String toString() {
		return "Mesas : Numero da mesa: " + numMesa + ", Capacidade de Pessoas: " + capacidadePessoas + "]";
	}
	
	public void exibirMesas(List<Mesas> mesas) {
		mesas.forEach(System.out::println);
	}
	
	public List<Mesas> adicionarMesas(List<Mesas> list) {
		Scanner sc = new Scanner(System.in);
		int opc = 0;
		while (opc != 2) {
			System.out.println("-------------------------");
			System.out.println("ADICIONANDO MESA");
			System.out.println("-------------------------");
			System.out.print("Digite o número da Mesa: ");
			int n = sc.nextInt();
			System.out.print("Digite a capacidade de pessoas: ");
			int cap = sc.nextInt();
		Mesas mesa = new Mesas(n, cap);
		list.add(mesa);
		System.out.println("-------------------------");
		System.out.print("Deseja adicionar outra mesa? 1-SIM | 2-NAO :");
		opc = sc.nextInt();
		
		}
		System.out.println("\n\n-------------------------");
		System.out.println("OK! CADASTRO DE MESAS FINALIZADO COM SUCESSO!");
		System.out.println("-------------------------\n\n");
		return list;
	}
	
	public void imprimirMesas(List<Mesas> list) {
		list.forEach(System.out::println);
	}



	public Integer getIDMesa() {
		return IDMesa;
	}



	public void setIDMesa(Integer iDMesa) {
		IDMesa = iDMesa;
	}
	
	
	
	
}

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

public class Bebidas {

	private Integer IDBebida;
	private String nome;
	private String tipoBebida;
	private Double valor;
	private String imagem;

	public Bebidas() {					//Classe Bebidas que compoe o Cardapio de Cada estabelecimento
	}

	public Bebidas(Integer iDBebida, String nome, String tipoBebida, Double valor, String imagem) {
		this.IDBebida = iDBebida;
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

	public void imprimirBebidas(List<Bebidas> bebidas) {   //Metodo que retorna uma lista de bebidas cadastradas no Cardapio de um estabelecimento
		if (bebidas == null) {
			System.out.println("A Lista de Bebidas esta vazia!");
		} else {
			System.out.println("--------------------------------");
			System.out.println("BEBIDAS DO CARDAPIO");
			System.out.println("--------------------------------");
			for (Bebidas obj : bebidas) {
				System.out.println(obj);
			}
			System.out.println("--------------------------------");
		}
	}

	@Override
	public String toString() {
		return "Bebidas [IDBebida=" + IDBebida + ", nome=" + nome + ", tipoBebida=" + tipoBebida + ", valor=" + valor
				+ ", imagem=" + imagem + "]";
	}

	public List<Bebidas> adicionarBebida() {				//Metodo responsavel receber os dados referente aos atributos e retornar um objeto do tipo Bebidas
		List<Bebidas> bebidasList = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		int opc = 0;

		while (opc != 2) {
			System.out.println("-------------------------");
			System.out.println("ADICIONANDO BEBIDA");
			System.out.println("-------------------------");

			System.out.print("Digite o nome da bebida: ");
			String nome = sc.nextLine();
			System.out.print("Digite o tipo da bebida: ");
			String tipoBebida = sc.nextLine();
			System.out.print("Digite o valor da bebida: ");
			Double valor = sc.nextDouble();
			sc.nextLine();
			System.out.print("Digite o caminho da imagem da bebida: ");
			String imagem = sc.nextLine();
			System.out.println("-------------------------");
			bebidasList.add(new Bebidas(null, nome, tipoBebida, valor, imagem));

			System.out.print("\n Deseja cadastrar outra bebida? 1-SIM / 2-NAO: ");
			opc = sc.nextInt();
		}
		System.out.println("\n\n\n-------------------------");
		System.out.println("CADASTRO DE BEBIDAS CONCLUIDO");
		System.out.println("-------------------------\n\n\n");
		return bebidasList;
	}
	
	public List<Bebidas> atualizarBebida(List<Bebidas> bebidasList) {			//Metodo que recebe uma Lista de Bebidas provinda de um Cardapio e atualiza conforme os novos dados.
		Scanner sc = new Scanner(System.in);
		int opc = 0;

		while (opc != 2) {
			System.out.println("-------------------------");
			System.out.println("ADICIONANDO BEBIDA");
			System.out.println("-------------------------");

			System.out.print("Digite o nome da bebida: ");
			String nome = sc.nextLine();
			System.out.print("Digite o tipo da bebida: ");
			String tipoBebida = sc.nextLine();
			System.out.print("Digite o valor da bebida: ");
			Double valor = sc.nextDouble();
			sc.nextLine();
			System.out.print("Digite o caminho da imagem da bebida: ");
			String imagem = sc.nextLine();
			System.out.println("-------------------------");
			bebidasList.add(new Bebidas(null, nome, tipoBebida, valor, imagem));

			System.out.print("\n Deseja cadastrar outra bebida? 1-SIM / 2-NAO: ");
			opc = sc.nextInt();
		}
		System.out.println("\n\n\n-------------------------");
		System.out.println("CADASTRO DE BEBIDAS CONCLUIDO");
		System.out.println("-------------------------\n\n\n");
		return bebidasList;
	}
	
}

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

public class Pratos {
	private Integer IDPrato;
	private String nome;
	private String tipoPrato;
	private String ingredientes;
	private Double valor;
	private String imagem;
	private List<Double> avaliacao;

	public Pratos() {
	}

	public Pratos(Integer iDPrato, String nome, String tipoPrato, String ingredientes, Double valor, String imagem,
			List<Double> avaliacao) {

		this.IDPrato = iDPrato;
		this.nome = nome;
		this.tipoPrato = tipoPrato;
		this.ingredientes = ingredientes;
		this.valor = valor;
		this.imagem = imagem;
		this.avaliacao = avaliacao;

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

	public Double getAvaliacao() {
		Double mediaAvaliacao = 0.0;
		for (Double obj : avaliacao) {
			mediaAvaliacao += obj;
		}
		mediaAvaliacao = mediaAvaliacao / (avaliacao.size());
		return mediaAvaliacao;

	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao.add(avaliacao);
	}

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
		return "[IDPrato=" + IDPrato + ", nome=" + nome + ", tipoPrato=" + tipoPrato + ", ingredientes=" + ingredientes
				+ ", valor=" + valor + ", imagem=" + imagem + ", avaliacao=" + "]";
	}

	public List<Pratos> adicionarPrato() {
		Scanner sc = new Scanner(System.in);
		List<Pratos> pratosList = new ArrayList<>();
		int opc = 0;

		while (opc != 2) {
			System.out.println("-------------------------");
			System.out.println("ADICIONANDO PRATO");
			System.out.println("-------------------------");

			System.out.print("Digite o nome do prato: ");
			String nome = sc.nextLine();
			System.out.print("Digite o tipo do prato: ");
			String tipoPrato = sc.nextLine();
			System.out.print("Digite os ingredientes do prato: ");
			String ingredientes = sc.nextLine();
			System.out.print("Digite o valor do Prato: ");
			Double valor = sc.nextDouble();
			System.out.print("Digite o caminho da imagem do prato: ");
			sc.nextLine();
			String imagem = sc.nextLine();
			System.out.println("-------------------------");
			List<Double> aval = new ArrayList<>();
			aval.add(5.0);
			pratosList.add(new Pratos(null, nome, tipoPrato, ingredientes, valor, imagem, aval));

			System.out.print("\n Deseja cadastrar outro prato? 1-SIM / 2-NAO: ");
			opc = sc.nextInt();
		}
		System.out.println("\n\n\n-------------------------");
		System.out.println("CADASTRO DE PRATOS CONCLUIDO");
		System.out.println("-------------------------\n\n\n");
		sc.nextLine();
		
		return pratosList;
	}



}

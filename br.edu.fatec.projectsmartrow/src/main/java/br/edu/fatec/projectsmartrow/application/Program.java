package br.edu.fatec.projectsmartrow.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.resources.EnderecoResource;

public class Program {
	public static void main(String[] args) throws SQLException {
		EnderecoResource enderecoresource = new EnderecoResource();
		Connection conn = ConexaoDB.getConnection();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("##########################");
		System.out.println("Teste de CEP");
		System.out.println("##########################");
		System.out.print("Digite um CEP: ");
		String cep = sc.nextLine();
		

		Endereco endereco = enderecoresource.CadastrarEnderecoAPI(cep);
		
		

		
	}

}

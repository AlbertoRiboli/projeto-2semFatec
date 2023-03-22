package br.edu.fatec.projectsmartrow.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.resources.EnderecoResource;
import br.edu.fatec.projectsmartrow.resources.EstabelecimentoResources;
import br.edu.fatec.projectsmartrow.services.EstabelecimentoService;

public class Program {
	public static void main(String[] args) throws SQLException {
		EnderecoResource enderecoresource = new EnderecoResource();
		EstabelecimentoResources estabelecimentoresource = new EstabelecimentoResources();
		Connection conn = ConexaoDB.getConnection();
		
		Scanner sc = new Scanner(System.in);
		

		
		
		estabelecimentoresource.adicionarEstabelecimento();
		
		
	
		
		
	}
}

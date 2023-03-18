package br.edu.fatec.projectsmartrow.application;

import java.sql.Connection;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;

public class Program {
	public static void main(String[] args) {

		Connection conn = ConexaoDB.getConnection();
	}

}

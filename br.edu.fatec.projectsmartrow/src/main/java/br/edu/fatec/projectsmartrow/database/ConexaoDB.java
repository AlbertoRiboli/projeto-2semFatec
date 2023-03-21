package br.edu.fatec.projectsmartrow.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import br.edu.fatec.projectsmartrow.exceptions.ExcessaoArquivoNaoEncontrado;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;

public class ConexaoDB {

	private static Connection conn = null;

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream(
				".\\src\\main\\java\\br\\edu\\fatec\\projectsmartrow\\database\\db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new ExcessaoArquivoNaoEncontrado(e.getMessage() + "Arquivo de Propriedades não foi encontrado!");
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url);
				System.out.println("Log Teste: Banco de Dados Conectado");
			} catch (SQLException e) {
				throw new ExcessaoConexaoDB(e.getMessage() + " | Erro ao acessar Banco de Dados");
			}
		}

		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ExcessaoSQL(e.getMessage() + " | Erro ao tentar encerrar conexão com Banco de Dados");
			}
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new ExcessaoSQL(e.getMessage() + " | Erro ao tentar encerrar o PrepareStatement");
			}
		}

	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new ExcessaoSQL(e.getMessage() + " | Erro ao tentar encerrar o Result Set");
			}
		}
	}

}

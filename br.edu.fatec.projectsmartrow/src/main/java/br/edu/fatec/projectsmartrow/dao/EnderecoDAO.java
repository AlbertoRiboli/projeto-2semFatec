package br.edu.fatec.projectsmartrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;
import br.edu.fatec.projectsmartrow.model.Endereco;

public class EnderecoDAO {

	public void insertEndereco(Endereco endereco) {
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"CREATE TABLE IF NOT EXISTS ENDERECO "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "CEP VARCHAR(10), "
					+ "LOGRADOURO VARCHAR(100),"
					+ "NUMERO VARCHAR(10),"
					+ "COMPLEMENTO VARCHAR(100),"
					+ "REFERENCIA VARCHAR(100),"
					+ "BAIRRO VARCHAR(100),"
					+ "LOCALIDADE VARCHAR(20),"
					+ "UF VARCHAR(2),"
					+ "PAIS VARCHAR(100)); ");
			ps.executeUpdate();
					
					
			ps1 = conn.prepareStatement(
					 "INSERT INTO ENDERECO "
					+ "(CEP, LOGRADOURO, NUMERO, COMPLEMENTO, REFERENCIA, BAIRRO, LOCALIDADE, UF, PAIS) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			ps1.setString(1, endereco.getCep());
			ps1.setString(2, endereco.getLogradouro());
			ps1.setString(3, endereco.getNumero());
			ps1.setString(4, endereco.getComplemento());
			ps1.setString(5, endereco.getReferencia());
			ps1.setString(6, endereco.getBairro());
			ps1.setString(7, endereco.getLocalidade());
			ps1.setString(8, endereco.getUf());
			ps1.setString(9, endereco.getPais());
			
//			ps1.executeUpdate();
			int registroModificados = ps1.executeUpdate();
			if(registroModificados > 0) {
				ResultSet rss = ps1.getGeneratedKeys();
				if(rss.next()) {
					int id = rss.getInt(1);
					endereco.setId(id);
					ConexaoDB.closeResultSet(rss);
				}
				else {
					throw new ExcessaoSQL("Erro ao recuperar Id em Banco de Dados");
				}
			System.out.println("Registros modificados: " + registroModificados);
			}
		
			
		}
		catch (SQLException e) {
			throw new ExcessaoConexaoDB("Erro ao manupular Banco de Dados: " + e.getMessage());
		}
		finally {
//			ConexaoDB.closeConnection();
//			ConexaoDB.closeResultSet(rs);
//			ConexaoDB.closeStatement(ps);
//			ConexaoDB.closeStatement(ps1);
			
		}
		
	}
}

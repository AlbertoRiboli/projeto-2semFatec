package br.edu.fatec.projectsmartrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.model.Mesas;

public class EnderecoDAO {

	public void insertEndereco(Endereco endereco) {
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps1 = null;
		
		try {
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
	
	public Endereco buscarEnderecoPorEstabelecimentoId(int id) {
		try {
			Connection conn = ConexaoDB.getConnection();
			PreparedStatement ps = null;
			Endereco endereco = new Endereco();
			ps = conn.prepareStatement("SELECT ENDERECO.* "
					+ "FROM ENDERECO INNER JOIN ESTABELECIMENTO "
					+ "ON ENDERECO.IDENDERECO = ESTABELECIMENTO.ENDERECO "
					+ "WHERE ESTABELECIMENTO.ENDERECO = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			endereco = converterEmEndereco(rs);
			return endereco;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro na buscar de mesas por Id!" + e.getMessage());
		}
	}

	public Endereco converterEmEndereco(ResultSet rs) {
		try {
		Endereco endereco = new Endereco();
		while (rs.next()) {
			endereco.setCep(rs.getString("CEP"));
			endereco.setLogradouro(rs.getString("LOGRADOURO"));
			endereco.setNumero(rs.getString("NUMERO"));
			endereco.setComplemento(rs.getString("COMPLEMENTO"));
			endereco.setReferencia(rs.getString("REFERENCIA"));
			endereco.setBairro(rs.getString("BAIRRO"));
			endereco.setLocalidade(rs.getString("LOCALIDADE"));
			endereco.setUf(rs.getString("UF"));
			endereco.setPais(rs.getString("PAIS"));
		}
		return endereco;
		}
		catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao receber receber endereco via ResultSet " + e.getMessage());
		}
		
	}
	
	
}

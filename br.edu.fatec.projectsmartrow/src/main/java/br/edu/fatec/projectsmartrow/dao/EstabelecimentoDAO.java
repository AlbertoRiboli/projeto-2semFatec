package br.edu.fatec.projectsmartrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;

public class EstabelecimentoDAO {

	public void insertEstabelecimento(Estabelecimento estabelecimento) {
		CardapioDAO cdao = new CardapioDAO();
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		MesasDAO mdao = new MesasDAO();

		try {
			ps1 = conn.prepareStatement("INSERT INTO ESTABELECIMENTO "
					+ "(NOME, CNPJ, TELEFONE, TELEFONE2, EMAIL, HORARIOFUNCIONAMENTO, ABERTO, "
					+ "IMAGEMESTABELECIMENTO, ENDERECO) " + "VALUES (?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps1.setString(1, estabelecimento.getNome());
			ps1.setString(2, estabelecimento.getCnpj());
			ps1.setString(3, estabelecimento.getTelefone());
			ps1.setString(4, estabelecimento.getTelefone2());
			ps1.setString(5, estabelecimento.getEmail());
			ps1.setString(6, estabelecimento.getHorarioFuncionamento());
			ps1.setInt(7, estabelecimento.getAberto());
			ps1.setString(8, estabelecimento.getImagemEstabelecimento());
			ps1.setInt(9, estabelecimento.getEndereco().getId());
			
			int registroModificados = ps1.executeUpdate();
			if (registroModificados > 0) {
				ResultSet rss = ps1.getGeneratedKeys();
				if (rss.next()) {
					int id = rss.getInt(1);
					estabelecimento.setIDEstabelecimento(id);
					ConexaoDB.closeResultSet(rss);
				} else {
					throw new ExcessaoSQL("Erro ao recuperar Id em Banco de Dados");
				}
				System.out.println("Registros modificados: " + registroModificados);
			}
			if (estabelecimento.getCardapio() != null) {
				cdao.insertCardapio(estabelecimento.getCardapio(), estabelecimento);
			}
			if (estabelecimento.getMesas() != null) {
				mdao.insertMesas(estabelecimento);
			}
		} catch (SQLException e) {
			throw new ExcessaoConexaoDB("Erro ao manupular Banco de Dados: " + e.getMessage());
		} finally {
//			ConexaoDB.closeConnection();
//			ConexaoDB.closeResultSet(rs);
//			ConexaoDB.closeStatement(ps);
//			ConexaoDB.closeStatement(ps1);
		}
	}
	

}

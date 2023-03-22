package br.edu.fatec.projectsmartrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;
import br.edu.fatec.projectsmartrow.model.Cardapio;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;

public class CardapioDAO {

	public void insertCardapio(Cardapio cardapio, Estabelecimento estabelecimento) {
		Connection conn = ConexaoDB.getConnection();

		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			ps1 = conn.prepareStatement("INSERT INTO CARDAPIO " + "(ID_ESTABELECIMENTO) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);

			ps1.setInt(1, (estabelecimento.getIDEstabelecimento()));

			int registroModificados = ps1.executeUpdate();
			if (registroModificados > 0) {
				ResultSet rss = ps1.getGeneratedKeys();
				if (rss.next()) {
					int id = rss.getInt(1);
					cardapio.setIDCardapio(id);
					ps1 = conn.prepareStatement("UPDATE ESTABELECIMENTO SET CARDAPIO = ? WHERE ID = ?");
					ps1.setInt(1, id);
					ps1.setInt(2, estabelecimento.getIDEstabelecimento());
					ps1.executeUpdate();
					ConexaoDB.closeResultSet(rss);
				} else {
					throw new ExcessaoSQL("Erro ao recuperar Id em Banco de Dados");
				}
				System.out.println("Registros modificados: " + registroModificados);
			}

			for (int i = 0; i < cardapio.getPratos().size(); i++) {
				String nome = cardapio.getPratos().get(i).getNome();
				String tipoprato = cardapio.getPratos().get(i).getTipoPrato();
				String ingredientes = cardapio.getPratos().get(i).getIngredientes();
				Double valor = cardapio.getPratos().get(i).getValor();
				String imagem = cardapio.getPratos().get(i).getImagem();

				PreparedStatement ps = null;
				ps = conn.prepareStatement(
						"INSERT INTO PRATOS "
								+ "(NOME, TIPOPRATO, INGREDIENTES, VALOR, IMAGEM, IDCARDAPIO) VALUES (?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nome);
				ps.setString(2, tipoprato);
				ps.setString(3, ingredientes);
				ps.setDouble(4, valor);
				ps.setString(5, imagem);
				ps.setInt(6, cardapio.getIDCardapio());

				int pratosmodificados = ps.executeUpdate();
				if (pratosmodificados > 0) {
					ResultSet rss = ps.getGeneratedKeys();
					if (rss.next()) {
						int id = rss.getInt(1);
						cardapio.getPratos().get(i).setIDPrato(id);
					
						
						
					}
				}
			}
			if (cardapio.getBebidas() != null) {
				for (int i = 0; i < cardapio.getBebidas().size(); i++) {
					String nome = cardapio.getBebidas().get(i).getNome();
					String tipobebida = cardapio.getBebidas().get(i).getTipoBebida();
					Double valor = cardapio.getBebidas().get(i).getValor();
					String imagem = cardapio.getBebidas().get(i).getImagem();

					PreparedStatement ps = null;
					ps = conn.prepareStatement("INSERT INTO BEBIDAS "
							+ "(NOME, TIPOBEBIDA, VALOR, IMAGEM, IDCARDAPIO) VALUES " + "(?,?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, nome);
					ps.setString(2, tipobebida);
					ps.setDouble(3, valor);
					ps.setString(4, imagem);
					ps.setInt(5, cardapio.getIDCardapio());

					int bebidasmodificadas = ps.executeUpdate();
					if (bebidasmodificadas > 0) {
						ResultSet rss = ps.getGeneratedKeys();
						if (rss.next()) {
							int id = rss.getInt(1);
							cardapio.getBebidas().get(i).setIDBebida(id);
							
						}
					}
				}
			}

		} catch (SQLException e) {
			throw new ExcessaoConexaoDB("Erro ao manupular Banco de Dados: " + e.getMessage());
		} finally {
//			ConexaoDB.closeConnection();
//			ConexaoDB.closeResultSet(rs);
//			ConexaoDB.closeStatement(ps);
//			ConexaoDB.closeStatement(ps1);
//			
		}

	}
}

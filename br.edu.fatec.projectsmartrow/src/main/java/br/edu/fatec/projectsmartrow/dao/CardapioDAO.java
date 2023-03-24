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
import br.edu.fatec.projectsmartrow.model.Bebidas;
import br.edu.fatec.projectsmartrow.model.Cardapio;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;
import br.edu.fatec.projectsmartrow.model.Pratos;

public class CardapioDAO {

	public void insertCardapio(Cardapio cardapio, Estabelecimento estabelecimento) {  //Metodo Insert no Banco de Dados para a classe Cardapio
		Connection conn = ConexaoDB.getConnection();

		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			ps1 = conn.prepareStatement("INSERT INTO CARDAPIO " + "(ID_ESTABELECIMENTO) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);

			ps1.setInt(1, (estabelecimento.getIDEstabelecimento()));

			int registroModificados = ps1.executeUpdate();
			if (registroModificados > 0) {
				ResultSet rss = ps1.getGeneratedKeys();				//If retorna o Id gerado no Banco de Dados e grava no atributo
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
				String ingredientes = cardapio.getPratos().get(i).getIngredientes();		//Percorre a lista Pratos dentro do Cardapio e armazena em variaveis auxiliares
				Double valor = cardapio.getPratos().get(i).getValor();
				String imagem = cardapio.getPratos().get(i).getImagem();

				PreparedStatement ps = null;
				ps = conn.prepareStatement(
						"INSERT INTO PRATOS "
								+ "(NOME, TIPOPRATO, INGREDIENTES, VALOR, IMAGEM, IDCARDAPIO) VALUES (?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, nome);
				ps.setString(2, tipoprato);
				ps.setString(3, ingredientes);												//Insere no BD os valores armazenados nas variaveis auxiliares que serão reeprenchidas
				ps.setDouble(4, valor);														// no próximo ciclo do for
				ps.setString(5, imagem);
				ps.setInt(6, cardapio.getIDCardapio());

				int pratosmodificados = ps.executeUpdate();
				if (pratosmodificados > 0) {
					ResultSet rss = ps.getGeneratedKeys();
					if (rss.next()) {														//Caso concluido com sucerro, retorna o ID gerado no banco de dados
						int id = rss.getInt(1);
						cardapio.getPratos().get(i).setIDPrato(id);

					}
				}
			}
			if (cardapio.getBebidas() != null) {
				for (int i = 0; i < cardapio.getBebidas().size(); i++) {
					String nome = cardapio.getBebidas().get(i).getNome();
					String tipobebida = cardapio.getBebidas().get(i).getTipoBebida();		//Insere no BD os valores armazenados nas variaveis auxiliares que serão reeprenchidas
					Double valor = cardapio.getBebidas().get(i).getValor();					// no próximo ciclo do for
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

	public List<Pratos> buscarPratosPorCardapio(int id) {									//Busca no banco de dados os registros que tiverem o ID = ID cardapio
		try {
			List<Pratos> listpratos = new ArrayList();
			PreparedStatement ps = null;
			Connection conn = ConexaoDB.getConnection();
			ResultSet rs = null;
			ps = conn.prepareStatement("SELECT * FROM PRATOS WHERE IDCARDAPIO = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			listpratos = converterEmPratos(rs);													
			return listpratos;

		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao buscar pratos por Id de Estabelecimento: " + e.getLocalizedMessage());
		}

	}

	public List<Pratos> converterEmPratos(ResultSet rs) {									//Metodo para economia de código, serve para instancia um Prato usando como carga
		try {																				// o ResultSet recebido dos dados do BD
			List<Pratos> listpratos = new ArrayList();
			while (rs.next()) {
				Pratos pratos = new Pratos();
				pratos.setIDPrato(rs.getInt("IDPRATO"));
				pratos.setNome(rs.getString("NOME"));
				pratos.setTipoPrato(rs.getString("TIPOPRATO"));
				pratos.setIngredientes(rs.getString("INGREDIENTES"));
				pratos.setValor(rs.getDouble("VALOR"));
				pratos.setImagem(rs.getString("IMAGEM"));
//			pratos.setAvaliacao(valor);   //Aguardar a implementação do Sistema de Avaliação de Pratos	
				listpratos.add(pratos);
			}
			return listpratos;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao Converter SQL em Pratos: " + e.getMessage());
		}

	}

	public Cardapio converterEmCardapio(ResultSet rs) {										//Metodo para economia de código, serve para instancia um Cardapio usando como carga
		try {																				//o ResultSet recebido dos dados do BD
			Cardapio cardapio = new Cardapio();

			while (rs.next()) {
				cardapio.setIDCardapio(rs.getInt("IDCARDAPIO"));
			}
			List<Pratos> listpratos = new ArrayList();

			listpratos = buscarPratosPorCardapio(cardapio.getIDCardapio());
			cardapio.setPratos(listpratos);
			List<Bebidas> listbebidas = new ArrayList();
			listbebidas = buscarBebidasPorCardapio(cardapio.getIDCardapio());
			cardapio.setBebidas(listbebidas);

			return cardapio;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao Recuperar Cardapio do Banco de Dados: " + e.getMessage());
		}
	}

		public Cardapio buscarCardapioPorIdEstabelecimento(int id) {						//Busca no Banco de Dados o registro de Cardapio que contenha o ID = ID Estabelecimento
			try {
			Cardapio cardapio = new Cardapio();
			int result = 0;
			PreparedStatement ps = null;
			Connection conn = ConexaoDB.getConnection();
			ResultSet rs = null;
			ps = conn.prepareStatement("SELECT * FROM CARDAPIO WHERE ID_ESTABELECIMENTO = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getInt("IDCARDAPIO");
			}

			PreparedStatement ps1 = null;
			ResultSet rss = null;
			ps1 = conn.prepareStatement("SELECT * FROM CARDAPIO WHERE IDCARDAPIO = ?");
			ps1.setInt(1, result);
			rss = ps1.executeQuery();
			cardapio = converterEmCardapio(rss);

			return cardapio;

		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao Converter Id Estabelecimento em Cardapio: " + e.getLocalizedMessage());
		}

	}

	public List<Bebidas> buscarBebidasPorCardapio(int id) {									//Busca no BD e retorna em Lista de bebidas os registros com id = ID Cardapio
		try {
			List<Bebidas> listbebidas = new ArrayList();
			PreparedStatement ps = null;
			Connection conn = ConexaoDB.getConnection();
			ResultSet rs = null;
			ps = conn.prepareStatement("SELECT * FROM BEBIDAS WHERE IDCARDAPIO = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			listbebidas = converterEmBebidas(rs);
			return listbebidas;

		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao buscar pratos por Id de Estabelecimento: " + e.getLocalizedMessage());
		}

	}

	public List<Bebidas> converterEmBebidas(ResultSet rs) {									//Converte em Lista de Bebidas a carga recebida em ResultSet vinda do BD
		try {
			List<Bebidas> listbebidas = new ArrayList();
			while (rs.next()) {
				Bebidas bebidas = new Bebidas();
				bebidas.setIDBebida(rs.getInt("IDBEBIDA"));
				bebidas.setNome(rs.getString("NOME"));
				bebidas.setTipoBebida(rs.getString("TIPOBEBIDA"));
				bebidas.setValor(rs.getDouble("VALOR"));
				bebidas.setImagem(rs.getString("IMAGEM"));
				listbebidas.add(bebidas);
			}
			return listbebidas;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao Converter SQL em Bebidas: " + e.getMessage());
		}

	}
	
	public void deletarCardapioPorId(int id) {									//Busca no BD e retorna em Lista de bebidas os registros com id = ID Cardapio
		try {
			Connection conn = ConexaoDB.getConnection();
			PreparedStatement ps = null;
			ps = conn.prepareStatement("DELETE FROM BEBIDAS WHERE IDCARDAPIO = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps1 = null;
			ps1 = conn.prepareStatement("DELETE FROM PRATOS WHERE IDCARDAPIO = ?");
			ps1.setInt(1, id);
			ps1.executeUpdate();
			
			PreparedStatement ps2 = null;
			ps2 = conn.prepareStatement("DELETE FROM CARDAPIO WHERE IDCARDAPIO = ?");
			ps2.setInt(1, id);
			ps2.executeUpdate();
			

		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao Deletar Cardapio: " + e.getLocalizedMessage());
		}

	}

}

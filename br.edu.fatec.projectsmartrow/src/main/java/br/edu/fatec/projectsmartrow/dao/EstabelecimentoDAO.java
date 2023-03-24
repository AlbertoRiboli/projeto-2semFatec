package br.edu.fatec.projectsmartrow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoSQL;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;
import br.edu.fatec.projectsmartrow.resources.EnderecoResource;

public class EstabelecimentoDAO {

	public void insertEstabelecimento(Estabelecimento estabelecimento) {
		CardapioDAO cdao = new CardapioDAO();
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		MesasDAO mdao = new MesasDAO();
		

		try {
			ps1 = conn.prepareStatement(
					"INSERT INTO ESTABELECIMENTO "
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

	public Estabelecimento converterEmEstabelecimento(ResultSet rs) throws SQLException {
		Estabelecimento est = new Estabelecimento();
		CardapioDAO c = new CardapioDAO();
		est.setIDEstabelecimento(rs.getInt("ID"));
		est.setNome(rs.getString("NOME"));
		est.setCnpj(rs.getString("CNPJ"));
		est.setTelefone(rs.getString("TELEFONE"));
		est.setTelefone2(rs.getString("TELEFONE2"));
		est.setEmail(rs.getString("EMAIL"));
		est.setHorarioFuncionamento(rs.getString("HORARIOFUNCIONAMENTO"));
		est.setAberto(rs.getInt("ABERTO"));
		est.setImagemEstabelecimento(rs.getString("IMAGEMESTABELECIMENTO"));
//			est.setFaturamento(faturamento); Aguardar a implementação do método para instansiar Faturamento do Banco de Dados
		est.setCardapio(c.buscarCardapioPorIdEstabelecimento(rs.getInt("ID")));
		EnderecoDAO edao = new EnderecoDAO();
		est.setEndereco(edao.buscarEnderecoPorEstabelecimentoId(rs.getInt("ID")));
//			est.setAvaliacao(getAvaliacao());
		List<Mesas> listmesas = new ArrayList();
		MesasDAO mdao = new MesasDAO();
		est.setMesas(mdao.buscarMesaPorEstabelecimento(rs.getInt("ID")));
//			est.setCategoriaEstabelecimento(categoriaEstabelecimento);

		return est;

	}

	public Estabelecimento buscarEstabelecimentoPorId(int id) {
		try {
			Connection conn = ConexaoDB.getConnection();
			PreparedStatement ps = null;
			Estabelecimento estabelecimento = new Estabelecimento();
			ps = conn.prepareStatement("SELECT * FROM ESTABELECIMENTO WHERE ID = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			estabelecimento = converterEmEstabelecimento(rs);
			return estabelecimento;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro na buscar Estabelecimento por Id!" + e.getMessage());
		}
	}

	public void listarTodosEstabelecimentos() {
		try {
			Connection conn = ConexaoDB.getConnection();
			PreparedStatement ps = null;
			List<Estabelecimento> list = new ArrayList();
			ps = conn.prepareStatement("SELECT * FROM ESTABELECIMENTO");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Estabelecimento estabelecimento = new Estabelecimento();
				estabelecimento = converterEmEstabelecimento(rs);
				list.add(estabelecimento);
			}

			for (Estabelecimento obj : list) {
				obj.imprimirEstabelecimento();
			}

		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro na buscar de mesas por Id!" + e.getMessage());
		}
	}

	public Estabelecimento buscarEstabelecimentoPorCnpj(String cnpj) {
		try {
			Connection conn = ConexaoDB.getConnection();
			PreparedStatement ps = null;
			Estabelecimento estabelecimento = new Estabelecimento();
			ps = conn.prepareStatement("SELECT * FROM ESTABELECIMENTO WHERE CNPJ = ?");
			ps.setString(1, cnpj);
			ResultSet rs = ps.executeQuery();
			estabelecimento = converterEmEstabelecimento(rs);
			return estabelecimento;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro na buscar Estabelecimento por Id!" + e.getMessage());
		}
	}

	public void atualizarEstabelecimento(Estabelecimento estabelecimento) {

		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		MesasDAO mdao = new MesasDAO();
		Scanner sc = new Scanner(System.in);

		try {

			EnderecoResource er = new EnderecoResource();
			EnderecoDAO edao = new EnderecoDAO();
			Endereco endereco = new Endereco();

			ps1 = conn.prepareStatement("UPDATE ESTABELECIMENTO "
					+ "SET NOME=?, CNPJ=?, TELEFONE=?, TELEFONE2=?, EMAIL=?, HORARIOFUNCIONAMENTO=?, ABERTO=?, "
					+ "IMAGEMESTABELECIMENTO=?, ENDERECO=? WHERE ID = ?");

			ps1.setString(1, estabelecimento.getNome());
			ps1.setString(2, estabelecimento.getCnpj());
			ps1.setString(3, estabelecimento.getTelefone());
			ps1.setString(4, estabelecimento.getTelefone2());
			ps1.setString(5, estabelecimento.getEmail());
			ps1.setString(6, estabelecimento.getHorarioFuncionamento());
			ps1.setInt(7, estabelecimento.getAberto());
			ps1.setString(8, estabelecimento.getImagemEstabelecimento());
			endereco = edao.buscarEnderecoPorEstabelecimentoId(estabelecimento.getIDEstabelecimento());
			endereco = er.atualizarEndereco(endereco);

			ps1.setInt(9, estabelecimento.getEndereco().getId());
			ps1.setInt(10, estabelecimento.getIDEstabelecimento());

			int registroModificados = ps1.executeUpdate();

			System.out.println("Registros modificados: " + registroModificados);

			if (estabelecimento.getCardapio() != null) {
				CardapioDAO cdao = new CardapioDAO();
				cdao.insertCardapio(estabelecimento.getCardapio(), estabelecimento);
			}
			if (estabelecimento.getMesas() != null) {
				mdao.insertMesas(estabelecimento);
			}
		} catch (SQLException e) {
			throw new ExcessaoConexaoDB("Erro ao manupular Banco de Dados: " + e.getMessage());
		}
	}

	public void deletarEstabelecimentoPorCnpj()  {
		try {
		CardapioDAO cdao = new CardapioDAO();
		MesasDAO mdao = new MesasDAO();
		EnderecoDAO edao = new EnderecoDAO();
		EstabelecimentoDAO estdao = new EstabelecimentoDAO();
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------");
		System.out.println("DELETANDO ESTABELECIMENTO - ATENCAO! ESSE PROCESSSO E IRREVERSIVEL");
		System.out.println("------------------------------------------------------------------");
		System.out.print("Digite o CNPJ do Estabelecimento: ");
		String cnpj = sc.nextLine();

		Estabelecimento est = new Estabelecimento();
		est = buscarEstabelecimentoPorCnpj(cnpj);
		int idest = est.getIDEstabelecimento();
		int idend = est.getEndereco().getId();
		int idcard = est.getCardapio().getIDCardapio();
		int idmes = est.getIDEstabelecimento();

		cdao.deletarCardapioPorId(idcard);
		mdao.deletarMesasPorIdEstabelecimento(idmes);
		edao.deletarEnderecoPorId(idmes);
		
		ps = conn.prepareStatement("DELETE FROM ESTABELECIMENTO WHERE CNPJ = ?");
		ps.setString(1, cnpj);
		int registrosdeletados = ps.executeUpdate();
		if (registrosdeletados > 0) {
			System.out.println("------------------------------------------------------------------");
			System.out.println("                    ESTABELECIMENTO DELETADO                      ");
			System.out.println("------------------------------------------------------------------");
		}else {
			System.out.println("Erro ao Deletar Estabelecimento!");
		}
		
		
		}
		catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao Deletar Estabelecimento: " + e.getMessage());
		}
		

	}

}

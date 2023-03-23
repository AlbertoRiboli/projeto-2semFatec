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
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;

public class MesasDAO {

	public void insertMesas(Estabelecimento estabelecimento) {
		Connection conn = ConexaoDB.getConnection();

		PreparedStatement ps1 = null;
		ResultSet rs = null;

		try {
			if (estabelecimento.getMesas() != null) {
				for (int i = 0; i < estabelecimento.getMesas().size(); i++) {
					int numMesa = estabelecimento.getMesas().get(i).getNumMesa();
					int capacidadePessoas = estabelecimento.getMesas().get(i).getCapacidadePessoas();
					PreparedStatement ps = null;
					ps = conn.prepareStatement(
									"INSERT INTO MESAS "
											+ "(NUMMESAS, CAPACIDADE_PESSOAS, ID_ESTABELECIMENTO) VALUES " + "(?,?,?)",
									Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, numMesa);
					ps.setInt(2, capacidadePessoas);
					ps.setInt(3, estabelecimento.getIDEstabelecimento());

					int mesasmodificadas = ps.executeUpdate();
					if (mesasmodificadas > 0) {
						ResultSet rss = ps.getGeneratedKeys();
						if (rss.next()) {
							int id = rss.getInt(1);
							estabelecimento.getMesas().get(i).setIDMesa(id);
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
	
	public List<Mesas> buscarMesaPorEstabelecimento(int id) {
		try {
			Connection conn = ConexaoDB.getConnection();
			PreparedStatement ps = null;
			List<Mesas> list = new ArrayList();
			ps = conn.prepareStatement("SELECT * FROM MESAS WHERE ID_ESTABELECIMENTO = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			list = converterEmMesa(rs);
			return list;
		} catch (SQLException e) {
			throw new ExcessaoSQL("Erro ao buscar de mesas por Id!" + e.getMessage());
		}
	}

	public List<Mesas> converterEmMesa(ResultSet rs) throws SQLException {
		List<Mesas> list = new ArrayList();
		while (rs.next()) {
			Mesas mesa = new Mesas();
			mesa.setIDMesa(rs.getInt("IDMESAS"));
			mesa.setNumMesa(rs.getInt("NUMMESAS"));
			mesa.setCapacidadePessoas(rs.getInt("CAPACIDADE_PESSOAS"));
			list.add(mesa);
		}
		return list;
	}
}

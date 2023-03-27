package br.edu.fatec.projectsmartrow.application;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.fatec.projectsmartrow.dao.CardapioDAO;
import br.edu.fatec.projectsmartrow.dao.EnderecoDAO;
import br.edu.fatec.projectsmartrow.dao.EstabelecimentoDAO;
import br.edu.fatec.projectsmartrow.dao.MesasDAO;
import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.resources.EstabelecimentoResources;
import br.edu.fatec.projectsmartrow.services.EnderecoService;

public class ProgramTest {

	public static void main(String[] args) throws FileNotFoundException, SQLException {
		
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		CardapioDAO cdao = new CardapioDAO();
		MesasDAO mdao = new MesasDAO();
		
		Estabelecimento est = new Estabelecimento();
		est = dao.buscarEstabelecimentoPorId(20);
		est.imprimirEstabelecimento();
		est.getCardapio().imprimirCardapio();
		System.out.println("IDCARPDAIO: " + est.getCardapio().getIDCardapio());
		
		
		
		dao.listarTodosEstabelecimentos();
		
		
		
	}}

	
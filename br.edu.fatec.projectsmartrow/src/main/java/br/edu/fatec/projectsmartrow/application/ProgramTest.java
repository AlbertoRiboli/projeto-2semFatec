package br.edu.fatec.projectsmartrow.application;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.fatec.projectsmartrow.dao.CardapioDAO;
import br.edu.fatec.projectsmartrow.dao.EstabelecimentoDAO;
import br.edu.fatec.projectsmartrow.dao.MesasDAO;
import br.edu.fatec.projectsmartrow.database.ConexaoDB;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;

public class ProgramTest {

	public static void main(String[] args) throws FileNotFoundException, SQLException {
		
		Connection conn = ConexaoDB.getConnection();
		PreparedStatement ps = null;
		EstabelecimentoDAO dao = new EstabelecimentoDAO();
		CardapioDAO cdao = new CardapioDAO();
		MesasDAO mdao = new MesasDAO();
//		
//		Endereco endereco = new Endereco("cep", "log", "num", "comple", "ref", "bairro", "dsadsaloc", "sp", "paisdd");
//		endereco.setId(10);
//		Estabelecimento estabelecimento = new Estabelecimento("nometeste", "cnpjaaaa", "tel1", "tel2", "email1","horario", 1, "imagemaqui", endereco, null);
//		dao.insertEstabelecimento(estabelecimento);
//		List<Pratos> pratos = new ArrayList<>();
//		Pratos p1 = new Pratos(null, "nome prato", "almoco", "cebola", 120.00, "iamgem", null );
//		Pratos p2 = new Pratos(null, "nome prato2", "almoco", "cebola", 120.00, "iamgem", null );
//		pratos.add(p1);
//		pratos.add(p2);
//		
//		List<Bebidas> bebidas = new ArrayList<>();
//		Bebidas b1 = new Bebidas(null, "nome bebida", "refri", 120.00, "iamgem");		
//		Bebidas b2 = new Bebidas(null, "nome bebida2", "refri", 120.00, "iamgem");
//		bebidas.add(b1);
//		bebidas.add(b2);
//		Cardapio cardapio = new Cardapio(null, pratos, bebidas);
//		estabelecimento.setCardapio(cardapio);
//		cdao.insertCardapio(cardapio, estabelecimento);
//		
//		Mesas m1 = new Mesas(10, 20);
//		Mesas m2 = new Mesas(10, 20);
//		List<Mesas> mesas = new ArrayList();
//		mesas.add(m1);
//		mesas.add(m2);
//		estabelecimento.setMesas(mesas);
//		mdao.insertMesas(estabelecimento);
//	
//		Mesas mesa = new Mesas();
//		ps = conn.prepareStatement("SELECT * FROM MESAS WHERE ID_ESTABELECIMENTO = 17");
//		
//		ResultSet rs = ps.executeQuery();
//		List<Mesas> list = new ArrayList<>(); 
//		list = mesa.buscarMesaPorEstabelecimento(23);
//		mesa.imprimirMesas(list);
//		List<Pratos> list = new ArrayList<>();
//		Pratos pratos = new Pratos();
//		list = pratos.buscarPratosPorCardapio(14);
//		pratos.imprimirPratos(list);
		
//		Cardapio cardapio = new Cardapio();
//		cardapio = cardapio.buscarCardapioPorIdEstabelecimento(23);
//		cardapio.imprimirCardapio();
		
		Estabelecimento e1 = new Estabelecimento();
		EstabelecimentoDAO edao = new EstabelecimentoDAO();
		e1 = edao.buscarEstabelecimentoPorCnpj("12345");
	
		e1.getCardapio().adicionarCardapio();
		
		
	}}

	
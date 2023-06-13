//package br.edu.fateccotia.projectsmartrow.config;
//
//import java.util.Arrays;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//import br.edu.fateccotia.projectsmartrow.controllers.EnderecoController;
//import br.edu.fateccotia.projectsmartrow.model.Cardapio;
//import br.edu.fateccotia.projectsmartrow.model.Endereco;
//import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
//import br.edu.fateccotia.projectsmartrow.model.Mesas;
//import br.edu.fateccotia.projectsmartrow.model.Pratos;
//import br.edu.fateccotia.projectsmartrow.repository.BebidasRepository;
//import br.edu.fateccotia.projectsmartrow.repository.CardapioRepository;
//import br.edu.fateccotia.projectsmartrow.repository.EnderecoRepository;
//import br.edu.fateccotia.projectsmartrow.repository.EstabelecimentoRepository;
//import br.edu.fateccotia.projectsmartrow.repository.MesasRepository;
//import br.edu.fateccotia.projectsmartrow.repository.PratosRepository;
//
//
//public class TestConfig implements CommandLineRunner {
//	
//	@Autowired
//	EstabelecimentoRepository estabelecimentoRepository;
//	@Autowired
//	EnderecoRepository enderecoRepostory;
//	@Autowired
//	MesasRepository mesasRepository;
//	@Autowired
//	PratosRepository pratosRepository;
//	@Autowired
//	BebidasRepository bebidasepository;
//	@Autowired
//	CardapioRepository cardapioRepository;
//	
//	EnderecoController enderecoController;
//	
//	@Override
//	public void run(String... args) throws Exception {
//		//Enderecos
//		Endereco end1 = new Endereco("06721100", "Estrada das Mulatas", 
//				"247", "csa", "mercado", "tjuco", "cotia", "sp", "Brasil");
//		
//		Endereco end2 = new Endereco("06721200", "Estrada das Mulatas 2", 
//				"247 2", "csa 2", "mercado 2", "tjuco", "cotia", "sp", "Brasil");
//		
//		enderecoRepostory.saveAll(Arrays.asList(end1, end2));
//		
//		//Mesas
//				Mesas m1 = new Mesas(1, 200);
//				Mesas m2 = new Mesas(2, 200);
//				Mesas m3 = new Mesas(3, 200);
//				Mesas m4 = new Mesas(4, 200);
//				Mesas m5 = new Mesas(5, 200);
//				Mesas m6 = new Mesas(6, 200);
//				
//				mesasRepository.saveAll(Arrays.asList(m1, m2, m3 , m4, m5, m6));
//				
//		//Estabelecimentos
//		Estabelecimento est1 = new Estabelecimento("Lanchonete 999999", "12345" , "11000000",
//				"22444444", "123@email", "10 as 18", "sem imagem", end1, null); 
//		
//		Estabelecimento est2 = new Estabelecimento("Lanchonete 888888", "5678" , "11000000",
//				"22444444", "123@email", "10 as 18", "sem imagem", end2, null); 
//		
//		est1.setAddMesas(m1);
//		est1.setAddMesas(m2);
//		est1.setAddMesas(m3);
//		
//		est2.setAddMesas(m4);
//		est2.setAddMesas(m5);
//		est2.setAddMesas(m6);
//		
//		estabelecimentoRepository.saveAll(Arrays.asList(est1, est2));
//		
//		//Pratos
//		Pratos p1 = new Pratos("ContraFile", "Almoço", "Arroz e Feijao", 15.99, "Sem Imagem");
//		Pratos p2 = new Pratos("Marcarrao", "Almoço", "Macarrao", 15.99, "Sem Imagem");
//		pratosRepository.saveAll(Arrays.asList(p1,p2));
//		
//		Cardapio card1 = new Cardapio();
//		card1.setListPratos(Arrays.asList(p1, p2));
//		cardapioRepository.saveAll(Arrays.asList(card1));
//		
//		
//		
//	}
//
//}

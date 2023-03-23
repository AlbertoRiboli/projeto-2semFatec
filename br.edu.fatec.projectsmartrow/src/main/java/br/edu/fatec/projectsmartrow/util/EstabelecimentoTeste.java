package br.edu.fatec.projectsmartrow.util;

import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.projectsmartrow.model.Bebidas;
import br.edu.fatec.projectsmartrow.model.Cardapio;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;
import br.edu.fatec.projectsmartrow.model.Pratos;

public class EstabelecimentoTeste {
	
	public Estabelecimento estabelecimentoteste() {
		Estabelecimento est = new Estabelecimento();
		est.setIDEstabelecimento(1);
		est.setNome("Richard Guedes");
		est.setCnpj("1234556677866");
		est.setTelefone("11966966684");
		est.setTelefone2("11966966684");
		est.setEmail("Richard@gmail.com");
		est.setHorarioFuncionamento("09:00 as 13:00");
		est.setAberto(1);
		est.setImagemEstabelecimento("caminho da imagem");
		
		List<Pratos> pratos = new ArrayList<>();
		Pratos p1 = new Pratos(null, "nome prato", "almoco", "cebola", 120.00, "iamgem", null );
		Pratos p2 = new Pratos(null, "nome prato2", "almoco", "cebola", 120.00, "iamgem", null );
		pratos.add(p1);
		pratos.add(p2);
		
		List<Bebidas> bebidas = new ArrayList<>();
		Bebidas b1 = new Bebidas(null, "nome bebida", "refri", 120.00, "iamgem");		
		Bebidas b2 = new Bebidas(null, "nome bebida2", "refri", 120.00, "iamgem");
		bebidas.add(b1);
		bebidas.add(b2);
		Cardapio cardapio = new Cardapio(null, pratos, bebidas);
		est.setCardapio(cardapio);
		
		Endereco endereco = new Endereco("cep", "log", "num", "comple", "ref", "bairro", "dsadsaloc", "sp", "paisdd");
		endereco.setId(10);
		est.setEndereco(endereco);
		
		Mesas m1 = new Mesas(10, 20);
		Mesas m2 = new Mesas(10, 20);
		List<Mesas> mesas = new ArrayList();
		mesas.add(m1);
		mesas.add(m2);
		est.setMesas(mesas);
				return est;
		
	}

}

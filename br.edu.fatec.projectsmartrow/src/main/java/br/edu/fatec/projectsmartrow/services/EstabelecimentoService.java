package br.edu.fatec.projectsmartrow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.application.PrincipalEstabelecimento;
import br.edu.fatec.projectsmartrow.dao.EstabelecimentoDAO;
import br.edu.fatec.projectsmartrow.dao.MesasDAO;
import br.edu.fatec.projectsmartrow.model.Bebidas;
import br.edu.fatec.projectsmartrow.model.Cardapio;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;
import br.edu.fatec.projectsmartrow.model.Pratos;

public class EstabelecimentoService {
	Scanner sc = new Scanner(System.in);
	EstabelecimentoDAO dao = new EstabelecimentoDAO();
	MesasDAO mdao = new MesasDAO();

	/*
	 * Classe responsável em gerenciar as regras de intermedio das classes Resource e DAO 
	 */
	public void adicionarEstabelecimento(Estabelecimento estabelecimento) { //Metodo que recebe os objetos que compoem o Objeto estabelecimento, faz o gerenciamneto do fluxo da aplicação conforme escolha
		System.out.println("\n\n##########################");				//do usuário. 
		System.out.print("DESEJA ADICIONAR O CARDAPIO \nAO SEU CADASTRO? 1-SIM / 2-NAO: ");
		int opc = 0;
		boolean cond = true;
		while (cond == true) {
			opc = sc.nextInt();
			if (opc == 1) {
				Pratos pt = new Pratos();
				Bebidas bd = new Bebidas();
				List<Pratos> pratos = pt.adicionarPrato();
				System.out.println("\n\n--------------------------------");
				System.out.println("Deseja Cadastrar bebidas? 1-SIM | 2=NAO: ");
				System.out.println("--------------------------------");
				System.out.print("Digite a opcao: ");
				int opc1 = 0;
				List<Bebidas> bebidas = null;
				while (opc1 != 2) {
					opc1 = sc.nextInt();
					if (opc1 == 1) {
						bebidas = bd.adicionarBebida();
						opc1 = 2;
					} else if (opc1 == 2) {
						System.out.println("\n\nOk! Voce podera adicionar seu cardapio \nposteriormente nas opcoes do aplicativo\n\n");
						
					} else {
						System.out.print("Opção Inválida! Digite novamente: ");
					}
				}
				Cardapio cd = new Cardapio(null, pratos, bebidas);
				estabelecimento.setCardapio(cd);
				cond = false;
			} else if (opc == 2) {
				System.out.println("\n\n##########################");
				System.out.println("Ok! Voce podera adicionar seu cardapio \nposteriormente nas opcoes do aplicativo");
				cond = false;
			} else {
				System.out.println("Opção Invalida!");
				System.out.print("Digite novamente a opcao: ");
			}
		}
		System.out.println("##########################\n\n");

		System.out.println("##########################");
		System.out.print("DESEJA CADASTRAR AS MESAS \nDO ESTABELECIMENTO? 1-SIM / 2-NAO: ");
		opc = 0;
		cond = true;
		sc.nextLine();
		while (cond == true) {
			opc = sc.nextInt();
			if (opc == 1) {
				Mesas mesas = new Mesas();
				List<Mesas> listMesas = new ArrayList<>();
				listMesas = mesas.adicionarMesas(listMesas);
				estabelecimento.setMesas(listMesas);
				cond = false;
			} else if (opc == 2) {
				System.out.println("\n\n##########################");
				System.out.println("Ok! Voce podera cadastrar as mesas \nposteriormente nas opcoes do aplicativo");
				cond = false;
			} else {
				System.out.println("Opcao Invalida!");
				System.out.print("Digite novamente a opcao: ");
			}
		}
		System.out.println("##########################\n\n");
		dao.insertEstabelecimento(estabelecimento);  //Nesse ponto o estabelecimento objeto é repassado junto com seus compostos para a classe DAO responsável em armazenar no banco de dados
													// Esse objeto foi bem delicado de fazer, uma vez que existem n relacionamentos. Na aplicação ele recebe os objetos em si
		System.out.println("\n\n--------------------------------");	// No banco de dados, a tabela em questão recebe o ID condizente com o objeto referenciado.
		System.out.println("ESTABELECIMENTO ADICIONADO COM SUCESSO");
		System.out.println("\n\n--------------------------------");
		System.out.print("Deseja selecionado como usuário do sistema agora? 1-SIM | 2-NAO: ");
		int opc2 = sc.nextInt();
		if (opc2 == 1) {
			PrincipalEstabelecimento.setUsuarioEstabelecimento(estabelecimento);
			
		}
		
	}

	public void atualizarEstabelecimento(Estabelecimento estabelecimento) {  //Apenas repassa o objeto para a camada DAO para atualizar um estabelecimento já existente
		dao.atualizarEstabelecimento(estabelecimento);
	}

}

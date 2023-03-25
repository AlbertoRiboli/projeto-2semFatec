package br.edu.fatec.projectsmartrow.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.dao.CardapioDAO;
import br.edu.fatec.projectsmartrow.dao.EstabelecimentoDAO;
import br.edu.fatec.projectsmartrow.model.Cardapio;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;
import br.edu.fatec.projectsmartrow.resources.EnderecoResource;
import br.edu.fatec.projectsmartrow.resources.EstabelecimentoResources;

public class Principal {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); 			//Instancia do Scanner
		EstabelecimentoResources estabelecimentoresource = new EstabelecimentoResources();		//Instancia do Est. Resources para envio e recebimento de requisições
		PrincipalEstabelecimento principalestabelecimento = new PrincipalEstabelecimento();		//Classe auxiliar para o Menu com fluxo da aplicação para o Estabelecimento
		EstabelecimentoDAO estabelecimentodao = new EstabelecimentoDAO();						//Instancia do DAO do Estab. para solicitar requisições referentes a BD - REVISAR EM BREVE
		CardapioDAO cardapiodao = new CardapioDAO();											//Instancia do DAO do Cardapio para solicitar requisições referentes a BD - REVISAR EM BREVE

		int opc = 0;
		while (opc != 5) {

			// ESPAÇO RESERVADO PARA ENTRAR METODO DE VALIDACAO DA ESTRUTURA DE USUARIOS
			// DEVERA SER FEITO A AUTENTICACAO E O FLUXO DA APLCIAÇÃO DIRECIONADA DE ACORDO
			// COM O TIPO DE USUARIO

			// MODO DE ACESSO TEMPORARIO

			System.out.println("---------------------------------");
			System.out.println("MODO DE ACESSO TEMPORARIO");
			System.out.println("BEM VINDO AO SMART ROW 1.0");
			System.out.println("---------------------------------");
			System.out.println("1 - Estabelecimento");
			System.out.println("2 - Cliente: EM DESENVOLVIMENTO");
			System.out.println("3 - Cadastrar Estabelecimento");
			System.out.println("4 - Cadastrar Cliente EM DESENVOLVIMENTO");
			System.out.println("5 - Finalizar Sistema");
			System.out.println("---------------------------------");
			System.out.print("Digite a opcao: ");
			opc = sc.nextInt();
			switch (opc) {

			case 1:
//		if(tipoUsuario == 'E') { AGUARDANDO IMPLEMENTAÇÃO DA ESTRUTURA DE USUARIOS
				// MODO DE ACESSO TEMPORÁRIO

				if (opc == 1) {
					int opt = 999;
					while (opt != 0) {
//						sc.nextLine();
						principalestabelecimento.imprimirmenuEstabelecimento();

						opt = sc.nextInt();
						switch (opt) {

						case 1:
							estabelecimentoresource.adicionarEstabelecimento();			//Opção para Cadastrar novo Estabelecimento 
							break;
						case 2:
							System.out.print("Digite o CNPJ do Estabelecimento: ");
							sc.nextLine();
							String cnpj = sc.nextLine();
							Estabelecimento estabelecimento = new Estabelecimento();	//Opção para selecionar novo Estabelecimento por CNPJ
							estabelecimento = estabelecimentodao.buscarEstabelecimentoPorCnpj(cnpj);
							estabelecimento.imprimirEstabelecimento();
							principalestabelecimento.setUsuarioEstabelecimento(estabelecimento);
							break;
						case 3:
							System.out.print("Digite o CNPJ do Estabelecimento: ");
							sc.nextLine();
							String cnpj1 = sc.nextLine();								//Opção para atualizar novo Estabelecimento buscando por CNPJ
							Estabelecimento estabelecimento1 = new Estabelecimento();
							estabelecimento1 = estabelecimentodao.buscarEstabelecimentoPorCnpj(cnpj1);
							estabelecimentoresource.atualizarEstabelecimento(estabelecimento1);
							
							break;
						case 4:
							estabelecimentodao.listarTodosEstabelecimentos();			//Busca no Banco de Dados e Lista todos os estabelecimentos
							break;
						case 5:
							Cardapio cardapio = new Cardapio();
							
							if(principalestabelecimento.getUsuarioEstabelecimento().getCardapio() != null) {
								cardapio = principalestabelecimento.getUsuarioEstabelecimento().getCardapio();
								cardapio.adicionarCardapio();
								principalestabelecimento.getUsuarioEstabelecimento().setCardapio(cardapio);			//Adiciona Novo Cardapio - EM REVISÃO:
								cardapiodao.insertCardapio(cardapio, principalestabelecimento.getUsuarioEstabelecimento()); //Ao inves de somar ao Cardapio existente 
																															//o mesmo está substituindo
							}
							else {
							cardapio.adicionarCardapio();
							principalestabelecimento.getUsuarioEstabelecimento().setCardapio(cardapio);
							cardapiodao.insertCardapio(cardapio, principalestabelecimento.getUsuarioEstabelecimento());
							}
							
							
							break;
						case 6:
							if (principalestabelecimento.getUsuarioEstabelecimento().getCardapio() != null) {
								principalestabelecimento.getUsuarioEstabelecimento().getCardapio().imprimirCardapio();		//Busca no Banco de Dados o cardapio referente ao Estabelecimento 
							} else {
								System.out.println("O Cardapio está vazio!");
							}
							break;
						case 7:
							Mesas m = new Mesas();
							List<Mesas> list = new ArrayList<>();
							if (principalestabelecimento.getUsuarioEstabelecimento().getMesas() != null) {					//Adiciona mesas ao estabelecimento
								list = principalestabelecimento.getUsuarioEstabelecimento().getMesas();
							}
							m.adicionarMesas(list);
							m.imprimirMesas(principalestabelecimento.getUsuarioEstabelecimento().getMesas());
							break;
						case 8:
							Mesas m1 = new Mesas();
							System.out.println();
							if (principalestabelecimento.getUsuarioEstabelecimento().getMesas() != null) {					//Busca no Banco de dados as mesas cadastradas no estabelecimento
								m1.imprimirMesas(principalestabelecimento.getUsuarioEstabelecimento().getMesas());
							} else {
								System.out.println("A lista de Mesas está vazia!");
							}
							System.out.println();
							break;
						case 9:
							estabelecimentodao.deletarEstabelecimentoPorCnpj();
							break;
						case 0:
							System.out.println("\n\nRetornando ao Menu Anterior!\n\n");
							break;
						default:
							System.out.println(																				//Retorna ao menu anterior
									"Voce escolheu uma opcao invalida ou que ainda \nnao foi implementada! Tente novamente!");
							break;

						}

					}

				}
				break;
			case 3:
				EstabelecimentoResources estabelecimentoresources = new EstabelecimentoResources();
				estabelecimentoresources.adicionarEstabelecimento();
				
				break;
			case 5:
				System.out.println("\n\n\n\n\n|----------------------------------------|");
				System.out.println("|                                        |");
				System.out.println("|     Sistema finalizado pelo Usuario!   |");
				System.out.println("|                                        |");
				System.out.println("|----------------------------------------|");
				break;

			default:
				System.out.println(
						"Voce escolheu uma opcao invalida ou que ainda \nnao foi implementada! Tente novamente!");			//Opção que retorna ao usuário caso deigite opção errada
				break;
			}
		}

	}
}
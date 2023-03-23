package br.edu.fatec.projectsmartrow.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.edu.fatec.projectsmartrow.dao.CardapioDAO;
import br.edu.fatec.projectsmartrow.dao.EstabelecimentoDAO;
import br.edu.fatec.projectsmartrow.model.Cardapio;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Mesas;
import br.edu.fatec.projectsmartrow.resources.EstabelecimentoResources;

public class Principal {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EstabelecimentoResources estabelecimentoresource = new EstabelecimentoResources();
		PrincipalEstabelecimento principalestabelecimento = new PrincipalEstabelecimento();
		EstabelecimentoDAO estabelecimentodao = new EstabelecimentoDAO();
		CardapioDAO cardapiodao = new CardapioDAO();

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
			System.out.println("3 - Cadastrar Estabelecimento EM DESENVOLVIMENTO");
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
							estabelecimentoresource.adicionarEstabelecimento();
							break;
						case 2:
							System.out.print("Digite o CNPJ do Estabelecimento: ");
							sc.nextLine();
							String cnpj = sc.nextLine();
							Estabelecimento estabelecimento = new Estabelecimento();
							estabelecimento = estabelecimentodao.buscarEstabelecimentoPorCnpj(cnpj);
							estabelecimento.imprimirEstabelecimento();
							break;
						case 3:
							System.out.print("Digite o CNPJ do Estabelecimento: ");
							sc.nextLine();
							String cnpj1 = sc.nextLine();
							Estabelecimento estabelecimento1 = new Estabelecimento();
							estabelecimento1 = estabelecimentodao.buscarEstabelecimentoPorCnpj(cnpj1);
							
							break;
						case 4:
							estabelecimentodao.listarTodosEstabelecimentos();
							break;
						case 5:
							Cardapio cardapio = new Cardapio();
							cardapio.adicionarCardapio();
							principalestabelecimento.getUsuarioEstabelecimento().setCardapio(cardapio);
							break;
						case 6:
							if (principalestabelecimento.getUsuarioEstabelecimento().getCardapio() != null) {
								principalestabelecimento.getUsuarioEstabelecimento().getCardapio().imprimirCardapio();
							} else {
								System.out.println("O Cardapio está vazio!");
							}
							break;
						case 7:
							Mesas m = new Mesas();
							List<Mesas> list = new ArrayList<>();
							if (principalestabelecimento.getUsuarioEstabelecimento().getMesas() != null) {
								list = principalestabelecimento.getUsuarioEstabelecimento().getMesas();
							}
							m.adicionarMesas(list);
							m.imprimirMesas(principalestabelecimento.getUsuarioEstabelecimento().getMesas());
							break;
						case 8:
							Mesas m1 = new Mesas();
							System.out.println();
							if (principalestabelecimento.getUsuarioEstabelecimento().getMesas() != null) {
								m1.imprimirMesas(principalestabelecimento.getUsuarioEstabelecimento().getMesas());
							} else {
								System.out.println("A lista de Mesas está vazia!");
							}
							System.out.println();
							break;
						case 0:
							System.out.println("\n\nRetornando ao Menu Anterior!\n\n");
							break;
						default:
							System.out.println(
									"Voce escolheu uma opcao invalida ou que ainda \nnao foi implementada! Tente novamente!");
							break;

						}

					}

				}
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
						"Voce escolheu uma opcao invalida ou que ainda \nnao foi implementada! Tente novamente!");
				break;
			}
		}

	}
}
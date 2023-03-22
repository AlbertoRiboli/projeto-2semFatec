package br.edu.fatec.projectsmartrow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	public void adicionarEstabelecimento(Estabelecimento estabelecimento) {
		System.out.println("\n\n##########################");
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
//				List<Bebidas> bebidas = bd.adicionarBebida();
				Cardapio cd = new Cardapio(null, pratos, bebidas);
				estabelecimento.setCardapio(cd);
				cd.imprimirCardapio();
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
				
				mesas.imprimirMesas(listMesas);
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
		dao.insertEstabelecimento(estabelecimento);
		
	}

}

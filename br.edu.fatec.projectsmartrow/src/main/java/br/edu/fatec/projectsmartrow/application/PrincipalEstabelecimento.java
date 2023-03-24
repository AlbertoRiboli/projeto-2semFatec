package br.edu.fatec.projectsmartrow.application;

import java.util.Scanner;

import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.util.EstabelecimentoTeste;

public class PrincipalEstabelecimento {
	EstabelecimentoTeste et = new EstabelecimentoTeste();
	private static Estabelecimento estabelecimento = new Estabelecimento();  //Instancia do Objeto estabelecimento estático para que fique fixo no sistema até que mude o usuário
	
//	estabelecimento = et.estabelecimentoteste();
	Scanner sc = new Scanner(System.in);
	
	
	public void imprimirmenuEstabelecimento() {
		
		System.out.println("---------------------------------------------------------------");
		System.out.println("|                                                             |");
		System.out.println("|                    SMART ROW VERSAO 1.0                     |");
		System.out.println("|                                                             |");
		System.out.println("---------------------------------------------------------------");
		System.out.println("|" + estabelecimento.estabelecimentoCabecalho());
		System.out.println("---------------------------------------------------------------");
		System.out.println("|                  ESCOLHA A OPCAO DESEJADA                   |");
		System.out.println("---------------------------------------------------------------");
		System.out.println("| 1 - CADASTRAR NOVO ESTABELECIMENTO                          |");
		System.out.println("| 2 - BUSCAR ESTABELECIMENTO POR CNPJ                         |");
		System.out.println("| 3 - ALTERAR CADASTRO DE ESTABELECIMENTO (EM DEV)            |");
		System.out.println("| 4 - IMPRIMIR TODOS OS ESTABELECIMENTOS                      |");
		System.out.println("| 5 - ADICIONAR CARDAPIO (EM REVISAO. ESTA SUBSTITUINDO NO BD |");
		System.out.println("| 6 - CONSULTAR CARDAPIO                                      |");
		System.out.println("| 7 - ADICIONAR MESAS                                         |");
		System.out.println("| 8 - CONSULTAR MESAS                                         |");
		System.out.println("| 9 - DELETAR ESTABELECIMENTO POR CNPJ                        |");
		System.out.println("| 0 - SAIR                                                    |");		
		System.out.print(" DIGITE A OPCAO: ");
		
		
	}
	
	public static void setUsuarioEstabelecimento(Estabelecimento estabelecimento) {
		PrincipalEstabelecimento.estabelecimento = estabelecimento;
	}
	
	public static Estabelecimento getUsuarioEstabelecimento() {
		return PrincipalEstabelecimento.estabelecimento;
	}
	

}

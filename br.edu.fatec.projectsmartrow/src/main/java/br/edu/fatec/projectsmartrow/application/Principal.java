package br.edu.fatec.projectsmartrow.application;

import java.util.Scanner;

import br.edu.fatec.projectsmartrow.resources.EstabelecimentoResources;

public class Principal {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EstabelecimentoResources estabelecimentoresource = new EstabelecimentoResources();
		PrincipalEstabelecimento principalestabelecimento = new PrincipalEstabelecimento();

		int opc = 0;
		while (opc != 5) {

			// ESPAÇO RESERVADO PARA ENTRAR METODO DE VALIDACAO DA ESTRUTURA DE USUARIOS
			// DEVERA SER FEITO A AUTENTICACAO E O FLUXO DA APLCIAÇÃO DIRECIONADA DE ACORDO
			// COM O TIPO DE USUARIO

			// MODO DE ACESSO TEMPORARIO

		System.out.println("---------------------------------");
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

//		if(tipoUsuario == 'E') { AGUARDANDO IMPLEMENTAÇÃO DA ESTRUTURA DE USUARIOS
		// MODO DE ACESSO TEMPORÁRIO
		
		if (opc == 1) {
			int opt = 999;
			while (opt != 0) {
				principalestabelecimento.imprimirmenuEstabelecimento();
				opt = sc.nextInt();
				switch (opt) {

				case 1:
					estabelecimentoresource.adicionarEstabelecimento();
					break;

					
					
					
					
				}
			}
		}
	}
}
}
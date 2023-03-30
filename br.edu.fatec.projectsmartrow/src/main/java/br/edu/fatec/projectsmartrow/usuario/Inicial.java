package br.edu.fatec.projectsmartrow.usuario;

import java.util.List;
import java.util.Scanner;

public class Inicial {


	public void inicialUsuario() {
		Cadastro gerenciador = new Cadastro();
		Scanner scanner = new Scanner(System.in);
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			System.out.println("\n\n\nMENU DE OPÇÕES");
			System.out.println("1 - Cadastrar novo usuário");
			System.out.println("2 - Listar todos os usuários");
			System.out.println("3 - Consultar usuário");
			System.out.println("4 - Atualizar usuário");
			System.out.println("5 - Excluir usuário");
			System.out.println("6 - Sair");
			System.out.print("Escolha uma opção: ");

			    
			    int opcao = scanner.nextInt();
			    while(opcao!=6) {
			    scanner.nextLine();
				switch (opcao){

				case 1:
				    String nome, senha, email, cpf;
				    boolean nomeValido = false, senhaValida = false, emailValido = false, cpfValido = false;

				    do {
				        System.out.println("Digite como gostaria de ser chamado:");
				        nome = scanner.nextLine();
				        nomeValido = gerenciador.validarNome(nome);
				        if (!nomeValido) {
				            System.out.println("Nome inválido. Tente novamente.");
				        }
				    } while (!nomeValido);

				    do {
				        System.out.println("Digite sua senha:");
				        senha = scanner.nextLine();
				        senhaValida = gerenciador.validarSenha(senha);
				        if (!senhaValida) {
				            System.out.println("Senha inválida. Tente novamente.");
				        }
				    } while (!senhaValida);

				    do {
				        System.out.println("Digite o seu e-mail:");
				        email = scanner.nextLine();
				        emailValido = gerenciador.validarEmail(email);
				        if (!emailValido) {
				            System.out.println("Email inválido. Tente novamente.");
				        }
				    } while (!emailValido);

				    do {
				        System.out.println("Digite seu CPF:");
				        cpf = scanner.nextLine();
				        cpfValido = gerenciador.validarCpf(cpf);
				        if (!cpfValido) {
				            System.out.println("CPF inválido. Tente novamente.");
				        }
				    } while (!cpfValido);

				    Usuario novoUsuario = new Usuario(nome, email, senha, cpf);
				    gerenciador.adicionarUsuario(novoUsuario);
				    usuarioDAO.create(novoUsuario);
				    imprimirDadosUsuario(novoUsuario);

				    break;

				case 2:
				    System.out.println("LISTA DE USUÁRIOS");
				    List<Usuario> usuarios = usuarioDAO.readAll();
				    if (usuarios.isEmpty()) {
				        System.out.println("Nenhum usuário cadastrado.");
				    } else {
				        for (Usuario usuario : usuarios) {
				            imprimirDadosUsuario(usuario);
				            System.out.println();
				        }
				    }
				    break;

				case 3:
					System.out.print("Digite o nome do usuário: ");
					String nomeConsulta = scanner.nextLine();
				    Usuario usuarioConsulta = usuarioDAO.read(nomeConsulta);
				    if (usuarioConsulta == null) {
				        System.out.println("Usuário não encontrado.");
				    } else {
				        imprimirDadosUsuario(usuarioConsulta);
				    }
				    break;


				case 4:
					System.out.print("Digite o nome do usuário: ");
					String nomeAtualizacao = scanner.nextLine();
				    Usuario usuarioAtualizacao = usuarioDAO.read(nomeAtualizacao);
				    if (usuarioAtualizacao == null) {
				        System.out.println("Usuário não encontrado.");
				    } else {
				        System.out.print("Novo nome: ");
				        usuarioAtualizacao.setNome(scanner.nextLine());
				        System.out.print("Nova senha: ");
				        usuarioAtualizacao.setSenha(scanner.nextLine());
				        System.out.print("Novo CPF: ");
				        usuarioAtualizacao.setCPF(scanner.nextLine());
				        usuarioDAO.update(usuarioAtualizacao);
				        System.out.println("Usuário atualizado com sucesso.");
				    }
				    break;

			     case 5:
			    	 System.out.print("Digite o nome do usuário: ");
			    	 String nomeExclusao = scanner.nextLine();
			         usuarioDAO.delete(nomeExclusao);
			         System.out.println("Usuário excluído com sucesso.");
			         break;

			//	 case 6:
			  //      System.out.println("Opção temporariamente invalida!");
			    //    break;

				 case 6:
			        System.out.println("Obrigado pelo acesso!");
			        break;

				 default:
			        System.out.println("Opção Inválida!");
			    }
				System.out.println("MENU DE OPÇÕES");
				System.out.println("1 - Cadastrar novo usuário");
				System.out.println("2 - Listar todos os usuários");
				System.out.println("3 - Consultar usuário");
				System.out.println("4 - Atualizar usuário");
				System.out.println("5 - Excluir usuário");
				System.out.println("6 - Sair");
				System.out.print("Escolha uma opção: ");
					opcao = scanner.nextInt();
			    }
	
	}

    public static void imprimirDadosUsuario(Usuario usuario) {
        System.out.println("Nome: " + usuario.getNome());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Senha: " + "*".repeat(usuario.getSenha().length()));
        System.out.println("Endereço: " + usuario.getCPF());
    }
}
  

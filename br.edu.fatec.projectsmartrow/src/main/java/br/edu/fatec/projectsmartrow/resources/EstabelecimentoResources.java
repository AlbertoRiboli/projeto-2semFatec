package br.edu.fatec.projectsmartrow.resources;

import java.util.Scanner;

import br.edu.fatec.projectsmartrow.dao.EnderecoDAO;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Faturamento;
import br.edu.fatec.projectsmartrow.model.enums.CategoriaEstabelecimento;
import br.edu.fatec.projectsmartrow.services.EstabelecimentoService;
import br.edu.fatec.projectsmartrow.util.ValidadorDadosEntrada;

public class EstabelecimentoResources {
	Scanner sc = new Scanner(System.in);
	EnderecoResource enderecoresource = new EnderecoResource();
	EstabelecimentoService estabelecimentoservice = new EstabelecimentoService();
	ValidadorDadosEntrada valida = new ValidadorDadosEntrada();

	public void adicionarEstabelecimento() { // Método responsavel em coletar os dados informados pelo usuário em
												// conjunto com o endereco retornado pela API
		Estabelecimento estabelecimento = new Estabelecimento(); // Através da requisição do usuário. Com todos os dados instanciados, encapsula em um objeto do tipo Estabelecimento
		String nome = "", cnpj = "", telefone = "", telefone2 = "", email = "", horarioFuncionamento; // E repassa para o metodo da camada seguinte, Estabelecimentoservice.
		Faturamento faturamento;
		Endereco endereco = null;
		CategoriaEstabelecimento categoriaEstabelecimento;

		System.out.println("############################");
		System.out.println("EFETUANDO CADASTRO DE ESTABELECIMENTO");
		System.out.println("############################");
		boolean validaNome = false;
		while (validaNome == false) {
			System.out.print("Digite o nome do Estabelecimento: ");
			nome = sc.nextLine();
			validaNome = valida.validaNome(nome);
		}
		boolean validaCnpj = false;
		while (validaCnpj == false) {
			System.out.print("Digite o CNPJ do Estabelecimento[Apenas Numeros]: ");
			cnpj = sc.nextLine();
			validaCnpj = valida.validaCnpj(cnpj);
		}
		boolean validaTelefone = false;
		while (validaTelefone == false) {
			System.out.print("Digite o Telefone do Estabelecimento: ");
			telefone = sc.nextLine();
			validaTelefone = valida.validaTelefone(telefone);
		}

		validaTelefone = false;
		while (validaTelefone == false) {
			System.out.print("Digite o Segundo Telefone do Estabelecimento: ");
			telefone2 = sc.nextLine();
			validaTelefone = valida.validaTelefone(telefone2);
		}
		boolean validaEmail = false;
		while (validaEmail == false) {
			System.out.print("\n\nDigite o Email do Estabelecimento: ");
			email = sc.nextLine();
			validaEmail = valida.validaEmail(email);
		}
		System.out.print("Digite o Horario de Funcionamento do Estabelecimento: ");
		horarioFuncionamento = sc.nextLine();
		System.out.println("O Faturamento será cadastrado como [Padrao = 5%]\n\n");
		faturamento = new Faturamento("Padrao", 5.0);
		boolean validaCep = false;
		String cep = null;
		while (validaCep == false) {
			System.out.print("Digite o CEP do endereco Exemplo[00000000]: ");
			cep = sc.nextLine();
			validaCep = valida.validaCep(cep);
			
		}
		endereco = enderecoresource.CadastrarEnderecoAPI(cep);
		estabelecimento.setIDEstabelecimento(null);
		estabelecimento.setNome(nome);
		estabelecimento.setCnpj(cnpj);
		estabelecimento.setTelefone(telefone);
		estabelecimento.setTelefone2(telefone2);
		estabelecimento.setEmail(email);
		estabelecimento.setAberto(1);
		estabelecimento.setHorarioFuncionamento(horarioFuncionamento);
		estabelecimento.setFaturamento(faturamento);
		estabelecimento.setEndereco(endereco);

		estabelecimentoservice.adicionarEstabelecimento(estabelecimento);
	}

	public void atualizarEstabelecimento(Estabelecimento estabelecimento) { // Recebe um objeto do tipo estabelecimento
																			// e abre as variaveis para que o usuário
																			// possa inserir os novos dados
		String nome="", cnpj="", telefone="", telefone2="", email="", horarioFuncionamento;
		Faturamento faturamento;
//		Endereco endereco;  // Verificando Duplicidade no código
		CategoriaEstabelecimento categoriaEstabelecimento;

		System.out.println("--------------------------------------");
		System.out.println("ATUALIZANDO CADASTRO DE ESTABELECIMENTO");
		System.out.println("--------------------------------------");
		
		
		boolean validaNome = false;
		while (validaNome == false) {
			System.out.print("Digite o nome do Estabelecimento: ");
			nome = sc.nextLine();
			validaNome = valida.validaNome(nome);
		}
		boolean validaCnpj = false;
		while (validaCnpj == false) {
			System.out.print("Digite o CNPJ do Estabelecimento[Apenas Numeros]: ");
			cnpj = sc.nextLine();
			validaCnpj = valida.validaCnpj(cnpj);
		}
		boolean validaTelefone = false;
		while (validaTelefone == false) {
			System.out.print("Digite o Telefone do Estabelecimento: ");
			telefone = sc.nextLine();
			validaTelefone = valida.validaTelefone(telefone);
		}

		validaTelefone = false;
		while (validaTelefone == false) {
			System.out.print("Digite o Segundo Telefone do Estabelecimento: ");
			telefone2 = sc.nextLine();
			validaTelefone = valida.validaTelefone(telefone2);
		}
		boolean validaEmail = false;
		while (validaEmail == false) {
			System.out.print("\n\nDigite o Email do Estabelecimento: ");
			email = sc.nextLine();
			validaEmail = valida.validaEmail(email);
		}
		System.out.print("Digite o Horario de Funcionamento do Estabelecimento: ");
		horarioFuncionamento = sc.nextLine();
		
		
		System.out.println("O Faturamento será cadastrado como [Padrao = 5%]\n\n");
		faturamento = new Faturamento("Padrao", 5.0);
		EnderecoDAO edao = new EnderecoDAO();
		
//		endereco = edao.buscarEnderecoPorEstabelecimentoId(estabelecimento.getIDEstabelecimento());
//		enderecoresource.atualizarEndereco(endereco);

		estabelecimento.setNome(nome);
		estabelecimento.setCnpj(cnpj);
		estabelecimento.setTelefone(telefone);
		estabelecimento.setTelefone2(telefone2);
//		estabelecimento.setEndereco(endereco);
		estabelecimento.setEmail(email);
		estabelecimento.setAberto(1);
		estabelecimento.setHorarioFuncionamento(horarioFuncionamento);
		estabelecimento.setFaturamento(faturamento);

		estabelecimentoservice.atualizarEstabelecimento(estabelecimento);
	}

}

package br.edu.fatec.projectsmartrow.resources;

import java.util.Scanner;

import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.model.Estabelecimento;
import br.edu.fatec.projectsmartrow.model.Faturamento;
import br.edu.fatec.projectsmartrow.model.enums.CategoriaEstabelecimento;
import br.edu.fatec.projectsmartrow.services.EstabelecimentoService;

public class EstabelecimentoResources {
	Scanner sc = new Scanner(System.in);
	EnderecoResource enderecoresource = new EnderecoResource();
	EstabelecimentoService estabelecimentoservice = new EstabelecimentoService();

	public void adicionarEstabelecimento() {
		Estabelecimento estabelecimento = new Estabelecimento();
		String nome, cnpj, telefone, telefone2, email, horarioFuncionamento; 
		Faturamento faturamento;
		Endereco endereco;
		CategoriaEstabelecimento categoriaEstabelecimento;
		
		System.out.println("############################");
		System.out.println("EFETUANDO CADASTRO DE ESTABELECIMENTO");
		System.out.println("############################");
		System.out.print("Digite o nome do Estabelecimento: ");
//		sc.nextLine();
		nome = sc.nextLine();
		System.out.print("Digite o CNPJ do Estabelecimento: ");
		cnpj = sc.nextLine();
		System.out.print("Digite o Telefone do Estabelecimento: ");
		telefone = sc.nextLine();
		System.out.print("Digite o Segundo Telefone do Estabelecimento: ");
		telefone2 = sc.nextLine();
		System.out.print("\n\nDigite o Email do Estabelecimento: ");
		email = sc.nextLine();
		System.out.print("Digite o Horario de Funcionamento do Estabelecimento: ");
		horarioFuncionamento = sc.nextLine();
		System.out.println("O Faturamento será cadastrado como [Padrao = 5%]\n\n");
		faturamento = new Faturamento("Padrao", 5.0);
		System.out.print("Digite o CEP do endereço: ");
		String cep = sc.nextLine();
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

}

package br.edu.fatec.projectsmartrow.services;

import java.util.Scanner;

import br.edu.fatec.projectsmartrow.dao.EnderecoDAO;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.util.ValidadorDadosEntrada;

public class EnderecoService {
	
	private EnderecoDAO dao = new EnderecoDAO();
	
	//Classe que faz o intermédio entre as classes EndereçoResource e Endereço DAO. Aplica parte das regras referente as requisições 
	//fornecendo a transparência e atribuição das responsabilidades
	
	public Endereco cadastrarEnderecoAPI(String cep) {  //Função que recebe um cep como parâmetro e chama a função do CEP retornando o objeto endereço pré preenchido
		Scanner sc1 = new Scanner(System.in);			
		Endereco endereco = new Endereco();
		endereco = BuscaCepAPI.buscaCep(cep);
		System.out.print("Digite o numero da residencia: ");
		String num = sc1.nextLine();
		System.out.print("Digite o Complemento: ");
		String complemento = sc1.nextLine();
		System.out.print("Digite a referencia: ");
		String referencia = sc1.nextLine();
		
		endereco.setNumero(num);
		endereco.setComplemento(complemento);
		endereco.setReferencia(referencia);
		
		System.out.println("\n#############################");
		System.out.println("O endereco cadastrado foi: ");
		endereco.exibirEndereco();
		System.out.println("#############################");
		
//		sc1.close();
		dao.insertEndereco(endereco);  //Nesse ponto a função atribui o objeto instanciado para a classe responsável em armazenar o objeto no banco de dados
		return endereco;
				
	}
	
	public Endereco atualizarEndereco(Endereco endereco) {  //Método que faz o intermedio entre classe DAO e Resource para atualização de um objeto endereço já existente
		ValidadorDadosEntrada valida = new ValidadorDadosEntrada();
		Scanner sc1 = new Scanner(System.in);
		
		boolean validaCep = false;
		String cep = null;
		while (validaCep == false) {
			System.out.print("Digite o CEP do endereco Exemplo[00000000]: ");
			cep = sc1.nextLine();
			validaCep = valida.validaCep(cep);
		}
		endereco = BuscaCepAPI.atualizaEnderecobuscaCep(cep, endereco);

		System.out.print("Digite o numero da residencia: ");
		String num = sc1.nextLine();
		System.out.print("Digite o Complemento: ");
		String complemento = sc1.nextLine();
		System.out.print("Digite a referencia: ");
		String referencia = sc1.nextLine();
		
		endereco.setNumero(num);
		endereco.setComplemento(complemento);
		endereco.setReferencia(referencia);
		
		System.out.println("\n#############################");
		System.out.println("O endereco atualizado foi: ");
		endereco.exibirEndereco();
		System.out.println("#############################");
		
//		sc1.close();
		dao.atualizarEndereco(endereco);  //Nesse ponto o método chama a função na classe DAO para que possa armazenar o objeto de acordo com sua responsabilidade
		return endereco;
				
	}

}

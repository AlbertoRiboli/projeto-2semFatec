package br.edu.fatec.projectsmartrow.services;

import java.util.Scanner;

import br.edu.fatec.projectsmartrow.dao.EnderecoDAO;
import br.edu.fatec.projectsmartrow.model.Endereco;

public class EnderecoService {
	
	private EnderecoDAO dao = new EnderecoDAO();
	
	
	public Endereco cadastrarEnderecoAPI(String cep) {
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
		
		sc1.close();
		dao.insertEndereco(endereco);
		return endereco;
		
		
		
	}

}

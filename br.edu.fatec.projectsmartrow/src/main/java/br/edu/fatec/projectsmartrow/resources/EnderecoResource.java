package br.edu.fatec.projectsmartrow.resources;

import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.services.EnderecoService;

public class EnderecoResource {

	private EnderecoService service = new EnderecoService();			//Faz o contato inicial com as camadas a partir da aplicação, recebendo os argumentos e repasssando para as camadas porteriores.
	
	public Endereco CadastrarEnderecoAPI(String cep) {
		Endereco endereco = service.cadastrarEnderecoAPI(cep);
		return endereco;
	}
	
	public Endereco atualizarEndereco(Endereco endereco) {
		endereco = service.atualizarEndereco(endereco);
		return endereco;
	}
	
}

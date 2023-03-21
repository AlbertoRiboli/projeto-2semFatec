package br.edu.fatec.projectsmartrow.resources;

import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.services.EnderecoService;

public class EnderecoResource {

	private EnderecoService service = new EnderecoService();
	
	public Endereco CadastrarEnderecoAPI(String cep) {
		Endereco endereco = service.cadastrarEnderecoAPI(cep);
		return endereco;
	}
	
}

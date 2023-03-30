package br.edu.fatec.projectsmartrow.model;

public class EnderecoEstabelecimento extends Endereco {				//Classe que em futura implantação herdará a superclasse endereço

	private String tipoEndereco;

	public EnderecoEstabelecimento() {
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
}

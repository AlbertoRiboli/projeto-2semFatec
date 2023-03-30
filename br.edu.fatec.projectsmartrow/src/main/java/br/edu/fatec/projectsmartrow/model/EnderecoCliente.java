package br.edu.fatec.projectsmartrow.model;

public class EnderecoCliente extends Endereco { 			//Classe que em futura implantação herdará a superclasse Endereço

	private String tipoEndereco;

	public EnderecoCliente() {
	}

	public String getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(String tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}
}

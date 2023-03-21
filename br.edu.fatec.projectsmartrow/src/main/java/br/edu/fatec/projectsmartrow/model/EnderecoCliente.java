package br.edu.fatec.projectsmartrow.model;

public class EnderecoCliente extends Endereco {

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

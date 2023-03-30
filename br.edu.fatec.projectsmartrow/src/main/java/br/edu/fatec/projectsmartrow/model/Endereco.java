package br.edu.fatec.projectsmartrow.model;

public class Endereco {		//SuperClasse que gerará duas heranças: EndereçoClinte e EndereçoEstabelecimento (Será implantado futuramente)
	
	private Integer id;
	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String referencia;
	private String bairro;
	private String localidade;
	private String uf;
	private String pais;
	
	public Endereco () {}

public Endereco(String cep, String logradouro, String numero, String complemento, String referencia, String bairro,
			String localidade, String uf, String pais) {
	
		this.cep = cep;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.referencia = referencia;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.pais = pais;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Endereco [cep=" + cep + ", logradouro=" + logradouro + ", numero=" + numero + ", complemento="
				+ complemento + ", referencia=" + referencia + ", bairro=" + bairro + ", localidade=" + localidade
				+ ", uf=" + uf + ", pais=" + pais + "]";
	}
	
	public void exibirEndereco() {  //Metodo usado para imprimir um endereço. Nesse em espeial, usei a StringBuilder para ficar mais apresentavel a estrutura do print
		StringBuilder sb = new StringBuilder();
		sb.append("Endereco: " + logradouro + " | Num.: " + numero + " | Complemento: " + complemento);
		sb.append("\nBairro: " + bairro + " | Cidade: " + localidade + "\nUF: " + uf  );
		sb.append(" | Pais: " + pais + " | Referencia: " + referencia);
		System.out.println(sb);
	}
	
	
}

package br.edu.fatec.projectsmartrow.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.edu.fatec.projectsmartrow.application.PrincipalEstabelecimento;
import br.edu.fatec.projectsmartrow.model.enums.CategoriaEstabelecimento;

public class Estabelecimento {
	private Integer IDEstabelecimento;
	private String nome;
	private String cnpj;
	private String telefone;
	private String telefone2;
	private String email;
	private String horarioFuncionamento;
	private Integer aberto;
	private String imagemEstabelecimento;
	private Faturamento faturamento;
	private Cardapio cardapio;
	private Endereco endereco;
	private List<Double> avaliacao;
	private List<Mesas> mesas;
	private CategoriaEstabelecimento categoriaEstabelecimento;
	
	public Estabelecimento () {}
	
	

	public Estabelecimento(String nome, String cnpj, String telefone, String telefone2,
			String email, String horarioFuncionamento, Integer aberto, String imagemEstabelecimento, Endereco endereco,
			CategoriaEstabelecimento categoriaEstabelecimento) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.telefone2 = telefone2;
		this.email = email;
		this.horarioFuncionamento = horarioFuncionamento;
		this.aberto = aberto;
		this.imagemEstabelecimento = imagemEstabelecimento;
		this.endereco = endereco;
		this.categoriaEstabelecimento = categoriaEstabelecimento;
	}



	public Integer getIDEstabelecimento() {
		return IDEstabelecimento;
	}

	public void setIDEstabelecimento(Integer iDEstabelecimento) {
		IDEstabelecimento = iDEstabelecimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHorarioFuncionamento() {
		return horarioFuncionamento;
	}

	public void setHorarioFuncionamento(String horarioFuncionamento) {
		this.horarioFuncionamento = horarioFuncionamento;
	}

	public int getAberto() {
		return aberto;
	}

	public void setAberto(int aberto) {
		this.aberto = aberto;
	}

	public String getImagemEstabelecimento() {
		return imagemEstabelecimento;
	}

	public void setImagemEstabelecimento(String imagemEstabelecimento) {
		this.imagemEstabelecimento = imagemEstabelecimento;
	}

	public Faturamento getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(Faturamento faturamento) {
		this.faturamento = faturamento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CategoriaEstabelecimento getCategoriaEstabelecimento() {
		return categoriaEstabelecimento;
	}

	public void setCategoriaEstabelecimento(CategoriaEstabelecimento categoriaEstabelecimento) {
		this.categoriaEstabelecimento = categoriaEstabelecimento;
	}
	
	public Cardapio getCardapio() {
		return cardapio;
	}
	
	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public void setMesas(List<Mesas> list) {
		this.mesas = list;
	}
	
	public List<Mesas> getMesas(){
		return this.mesas;
	}

	public Double getAvaliacao() {
		Double mediaAvaliacao = 0.0;
		for(Double obj : avaliacao) {
			mediaAvaliacao += obj;
		}
		mediaAvaliacao = mediaAvaliacao / (avaliacao.size());
		return mediaAvaliacao;
	}

	public void setAvaliacao(Double avaliacao) {
		this.avaliacao.add(avaliacao);
	}

	@Override
	public String toString() {
		return "Estabelecimento [IDEstabelecimento =" + IDEstabelecimento + ", nome=" + nome + ", cnpj=" + cnpj + ", telefone=" + telefone + ", telefone2="
				+ telefone2 + ", email=" + email + ", horarioFuncionamento=" + horarioFuncionamento + ", aberto="
				+ aberto + ", imagemEstabelecimento=" + imagemEstabelecimento + ", faturamento=" + faturamento
				+ ", endereco=" + endereco + ", categoriaEstabelecimento=" + categoriaEstabelecimento + "]";
	}
	
	public Estabelecimento converterEmEstabelecimento (ResultSet rs) throws SQLException {
		Estabelecimento est = new Estabelecimento();
		est.setIDEstabelecimento(rs.getInt("ID"));
		est.setNome(rs.getString(rs.getString("NOME")));
		est.setCnpj(rs.getString(rs.getString("CNPJ")));
		est.setTelefone(rs.getString("TELEFONE"));
		est.setTelefone2(rs.getString("TELEFONE2"));
		est.setEmail(rs.getString("EMAIL"));
		est.setHorarioFuncionamento(rs.getString("HORARIOFUNCIONAMENTO"));
		est.setAberto(rs.getInt("ABERTO"));
		est.setImagemEstabelecimento(rs.getString("IMAGEMESTABELECIMENTO"));
//		est.setFaturamento(faturamento); Aguardar a implementação do método para instansiar Faturamento do Banco de Dados
//		est.setCardapio();
//		est.setEndereco(endereco);
//		est.setAvaliacao(getAvaliacao());
//		est.setMesas(mesas);
//		est.setCategoriaEstabelecimento(categoriaEstabelecimento);
		
		
		
		
		
		return est;
																					
		
	}

	

}

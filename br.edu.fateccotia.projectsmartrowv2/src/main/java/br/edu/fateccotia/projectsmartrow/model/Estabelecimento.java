package br.edu.fateccotia.projectsmartrow.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.fateccotia.projectsmartrow.model.enums.CategoriaEstabelecimento;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_estabelecimento")
public class Estabelecimento {
	@Id									//Classe que instanciará um Estabelecimento com suas composições, alguns são objetos gerados em outras classes
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDEstabelecimento;
	private String nome;
	private String cnpj;
	private String telefone;
	private String telefone2;
	private String email;
	private String senha;
	private boolean emailIsValid = false;
	private String horarioFuncionamento;
//	private Integer aberto;
	private String imagemEstabelecimento;
	@OneToOne
	private Faturamento faturamento;
	@OneToOne
	private Cardapio cardapio;
	@OneToOne
	private Endereco endereco;
	
	@OneToMany
	@JoinColumn(name = "idestabelecimento")
	private List<Mesas> mesas = new ArrayList<>();
	private CategoriaEstabelecimento categoriaEstabelecimento;

	public Estabelecimento() {
	}

	public Estabelecimento(String nome, String cnpj, String telefone, String telefone2, String email,
			String horarioFuncionamento, String imagemEstabelecimento, Endereco endereco,
			CategoriaEstabelecimento categoriaEstabelecimento) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.telefone2 = telefone2;
		this.email = email;
		this.horarioFuncionamento = horarioFuncionamento;
//		this.aberto = aberto;
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

//	public int getAberto() {
//		return aberto;
//	}
//
//	public void setAberto(int aberto) {
//		this.aberto = aberto;
//	}

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
	
	public void setAddMesas(Mesas mesa) {
		this.mesas.add(mesa);
	}
	

	public List<Mesas> getMesas() {
		return this.mesas;
	}

	
//	public Double getAvaliacao() {
//		Double mediaAvaliacao = 0.0;
//		for (Double obj : avaliacao) {
//			mediaAvaliacao += obj;
//		}
//		mediaAvaliacao = mediaAvaliacao / (avaliacao.size());
//		return mediaAvaliacao;
//	}
//
//	public void setAvaliacao(Double avaliacao) {
//		this.avaliacao.add(avaliacao);
//	}

	@Override
	public String toString() {
		return  nome  
				+ "\nTelefone: " + telefone + " Telefone2: " + telefone2 
				+ "\nHorário: " + horarioFuncionamento
				+ "\n" +endereco;
	}
	
	public String exibirEstabelecimento() {
		return  nome  
				+ "\nTelefone: " + telefone 
				+ "\nHorário: " + horarioFuncionamento
				+ "\n" +endereco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isEmailIsValid() {
		return emailIsValid;
	}

	public void setEmailIsValid(boolean emailIsValid) {
		this.emailIsValid = emailIsValid;
	}


	
	

}

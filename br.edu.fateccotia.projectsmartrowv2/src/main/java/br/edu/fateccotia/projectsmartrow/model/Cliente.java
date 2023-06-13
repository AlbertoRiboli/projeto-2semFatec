package br.edu.fateccotia.projectsmartrow.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDCliente;
	private String nome;
	private String email;
	private boolean emailIsValid = false;
	private String senha;
	private String rg;
	@Column(unique = true)
	private String cpf;
	@OneToOne
	private Endereco endereco;
	private String telefone;
	
	public Cliente () {}
	
	

	public Cliente(Integer iDCliente, String nome, String rg, String cpf, Endereco endereco, String telefone) {
		IDCliente = iDCliente;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public Integer getIDCliente() {
		return IDCliente;
	}

	public void setIDCliente(Integer iDCliente) {
		IDCliente = iDCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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



	@Override
	public String toString() {
		return "Cliente [IDCliente=" + IDCliente + ", nome=" + nome + ", email=" + email + ", emailIsValid="
				+ emailIsValid + ", senha=" + senha + ", rg=" + rg + ", cpf=" + cpf + ", endereco=" + endereco
				+ ", telefone=" + telefone + "]";
	}
	
	

}

package br.edu.fateccotia.projectsmartrow.model;

import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mesas")
public class Mesas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDMesa;
	private Integer numMesa;
	private Integer capacidadePessoas;
	private String enderecoQr;
	private String nomeArquivoQr;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idestabelecimento", insertable = false, updatable = false)
	private Estabelecimento estabelecimento;
	public Mesas() {
	}

	public Mesas(Integer numMesa, Integer capacidadePessoas) {
		this.numMesa = numMesa;
		this.capacidadePessoas = capacidadePessoas;
	}

	public Integer getIDMesa() {
		return IDMesa;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public void setIDMesa(Integer iDMesa) {
		IDMesa = iDMesa;
	}

	public Integer getNumMesa() {
		return numMesa;
	}

	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}

	public Integer getCapacidadePessoas() {
		return capacidadePessoas;
	}

	public void setCapacidadePessoas(Integer capacidadePessoas) {
		this.capacidadePessoas = capacidadePessoas;
	}

	@Override
	public String toString() {
		return 	"IDMesa: " + IDMesa
				+"\nNúmero da mesa: " + numMesa 
				+"\nCapacidade de Pessoas: " + capacidadePessoas;
	}

	
	public String getEnderecoQr() {
		return enderecoQr;
	}

	public void setEnderecoQr(String enderecoQr) {
		this.enderecoQr = enderecoQr;
	}

	public String getNomeArquivoQr() {
		return nomeArquivoQr;
	}

	public void setNomeArquivoQr(String nomeArquivoQr) {
		this.nomeArquivoQr = nomeArquivoQr;
	}
	
	

	


}

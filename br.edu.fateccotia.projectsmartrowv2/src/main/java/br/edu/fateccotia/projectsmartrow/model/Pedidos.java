package br.edu.fateccotia.projectsmartrow.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_pedidos")
public class Pedidos {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer IDPedido;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant instant;
	@OneToOne
	private Cliente cliente;

	@OneToOne
	private Estabelecimento estabelecimento;
	@ManyToMany
	private List<Pratos> listPratos = new ArrayList<>();
	@ManyToMany
	private List<Bebidas> listBebidas = new ArrayList<>();
	@OneToOne
	private Mesas mesa;
	private Double total;

	public Pedidos() {
	}

	public Pedidos(Cliente cliente, Estabelecimento estabelecimento, Mesas mesa) {
		this.instant = instant.now();
		this.cliente = cliente;
		this.estabelecimento = estabelecimento;
		this.mesa = mesa;
	}

	public Integer getIDPedido() {
		return IDPedido;
	}

	public void setIDPedido(Integer iDPedido) {
		IDPedido = iDPedido;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public List<Pratos> getListPratos() {
		return listPratos;
	}

	public void addPratoEmLista(Pratos pratos) {
		this.listPratos.add(pratos);
	}

	public List<Bebidas> getListBebidas() {
		return listBebidas;
	}

	public void addBebidaEmLista(Bebidas bebidas) {
		this.listBebidas.add(bebidas);
	}

	public Mesas getMesa() {
		return mesa;
	}

	public void setMesa(Mesas mesa) {
		this.mesa = mesa;
	}

	public Double getTotal() {
		Double soma = 0.00;
		if (listPratos != null) {
			for (Pratos prato : listPratos) {
				soma += prato.getValor();
			}
		}
		if (listBebidas != null) {
			for (Bebidas bebida : listBebidas) {
				soma += bebida.getValor();
			}
		}
		this.total = soma;

		return total;
	}

	public void setListPratos(List<Pratos> listPratos) {
		this.listPratos = listPratos;
	}

	public void setListBebidas(List<Bebidas> listBebidas) {
		this.listBebidas = listBebidas;
	}

	public void setTotal() {
		Double soma = 0.00;
		for (Pratos prato : listPratos) {
			soma += prato.getValor();
		}

		for (Bebidas bebida : listBebidas) {
			soma += bebida.getValor();
		}
		this.total = soma;
	}

}

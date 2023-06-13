package br.edu.fateccotia.projectsmartrow.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.model.Cardapio;
import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Endereco;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Pedidos;
import br.edu.fateccotia.projectsmartrow.model.Pratos;

public class JsonInObject {

	public static Estabelecimento stringToEstabelecimento(String stringSb) {
		JSONObject obj = new JSONObject(stringSb);
		Estabelecimento estabelecimento = new Estabelecimento();

		estabelecimento.setIDEstabelecimento(obj.getInt("idestabelecimento"));
		estabelecimento.setNome(obj.getString("nome"));
		estabelecimento.setCnpj(obj.getString("cnpj"));
		estabelecimento.setTelefone(obj.getString("telefone"));
		estabelecimento.setTelefone2(obj.getString("telefone2"));
		estabelecimento.setEmail(obj.getString("email"));
		estabelecimento.setSenha(obj.getString("senha"));
		estabelecimento.setEmailIsValid(obj.getBoolean("emailIsValid"));
		estabelecimento.setHorarioFuncionamento(obj.getString("horarioFuncionamento"));
		estabelecimento.setImagemEstabelecimento(null);
		estabelecimento.setFaturamento(null);

		if (obj.has("cardapio") && !obj.isNull("cardapio")) {
			JSONObject objCardapio = obj.getJSONObject("cardapio");
			Cardapio cardapio = new Cardapio();
			List<Pratos> listPratos = new ArrayList<>();

			JSONArray pratosArray = objCardapio.getJSONArray("pratos");
			if (pratosArray.isNull(0) != true) {
				for (int i = 0; i < pratosArray.length(); i++) {
					JSONObject pratojson = pratosArray.getJSONObject(i);
					Pratos newPrato = new Pratos();
					newPrato.setIDPrato(pratojson.getInt("idprato"));
					newPrato.setNome(pratojson.getString("nome"));
					newPrato.setTipoPrato(pratojson.getString("tipoPrato"));
					newPrato.setIngredientes(pratojson.getString("ingredientes"));
					newPrato.setValor(pratojson.getDouble("valor"));
					newPrato.setImagem(pratojson.getString("imagem"));

					listPratos.add(newPrato);
				}
				cardapio.setListPratos(listPratos);
			} else {
				cardapio.setListPratos(null);
			}

			JSONArray listobjBebidas = objCardapio.getJSONArray("bebidas");
			if (listobjBebidas.isNull(0) != true) {
				List<Bebidas> listBebidas = new ArrayList<>();
				for (int i = 0; i < listobjBebidas.length(); i++) {
					JSONObject objBebidas = listobjBebidas.getJSONObject(i);
					Bebidas bebida = new Bebidas();
					bebida.setIDBebida(objBebidas.getInt("idbebida"));
					bebida.setNome(objBebidas.getString("nome"));
					bebida.setTipoBebida(objBebidas.getString("tipoBebida"));
					bebida.setValor(objBebidas.getDouble("valor"));
					bebida.setImagem(objBebidas.getString("imagem"));

					listBebidas.add(bebida);

				}
				cardapio.setListBebidas(listBebidas);
			} else {
				cardapio.setListBebidas(null);
			}
			estabelecimento.setCardapio(cardapio);
		} else {
			estabelecimento.setCardapio(null);
		}

		if (obj.has("endereco") && !obj.isNull("endereco") && !obj.getJSONObject("endereco").isEmpty()) {
			JSONObject end = obj.getJSONObject("endereco");

			Endereco endereco = new Endereco();
			endereco.setCep(end.getString("cep"));
			endereco.setLocalidade(end.getString("localidade"));
			endereco.setLogradouro(end.getString("logradouro")); // Extrai do objeto Json e converte nos Sets dentro do
																	// Objeto Endereço
			endereco.setPais("Brasil");
			endereco.setUf(end.getString("uf"));
			endereco.setBairro(end.getString("bairro"));
			endereco.setId(end.getInt("id"));
			endereco.setNumero(end.getString("numero"));
			endereco.setComplemento(end.getString("complemento"));
			endereco.setReferencia(end.getString("referencia"));

			estabelecimento.setEndereco(endereco);

		}

		return estabelecimento;
	}

	public static Estabelecimento JSONToEstabelecimento(JSONObject obj) {
		Estabelecimento estabelecimento = new Estabelecimento();

		estabelecimento.setIDEstabelecimento(obj.getInt("idestabelecimento"));
		estabelecimento.setNome(obj.getString("nome"));
		estabelecimento.setCnpj(obj.getString("cnpj"));
		estabelecimento.setTelefone(obj.getString("telefone"));
		estabelecimento.setTelefone2(obj.getString("telefone2"));
		estabelecimento.setEmail(obj.getString("email"));
		estabelecimento.setSenha(obj.getString("senha"));
		estabelecimento.setEmailIsValid(obj.getBoolean("emailIsValid"));
		estabelecimento.setHorarioFuncionamento(obj.getString("horarioFuncionamento"));
		estabelecimento.setImagemEstabelecimento(null);
		estabelecimento.setFaturamento(null);

		if (obj.has("cardapio") && !obj.isNull("cardapio")) {
			JSONObject objCardapio = obj.getJSONObject("cardapio");
			Cardapio cardapio = new Cardapio();
			List<Pratos> listPratos = new ArrayList<>();

			JSONArray pratosArray = objCardapio.getJSONArray("pratos");
			if (pratosArray.isNull(0) != true) {
				for (int i = 0; i < pratosArray.length(); i++) {
					JSONObject pratojson = pratosArray.getJSONObject(i);
					Pratos newPrato = new Pratos();
					newPrato.setIDPrato(pratojson.getInt("idprato"));
					newPrato.setNome(pratojson.getString("nome"));
					newPrato.setTipoPrato(pratojson.getString("tipoPrato"));
					newPrato.setIngredientes(pratojson.getString("ingredientes"));
					newPrato.setValor(pratojson.getDouble("valor"));
					newPrato.setImagem(pratojson.getString("imagem"));

					listPratos.add(newPrato);
				}
				cardapio.setListPratos(listPratos);
			} else {
				cardapio.setListPratos(null);
			}

			JSONArray listobjBebidas = objCardapio.getJSONArray("bebidas");
			if (listobjBebidas.isNull(0) != true) {
				List<Bebidas> listBebidas = new ArrayList<>();
				for (int i = 0; i < listobjBebidas.length(); i++) {
					JSONObject objBebidas = listobjBebidas.getJSONObject(i);
					Bebidas bebida = new Bebidas();
					bebida.setIDBebida(objBebidas.getInt("idbebida"));
					bebida.setNome(objBebidas.getString("nome"));
					bebida.setTipoBebida(objBebidas.getString("tipoBebida"));
					bebida.setValor(objBebidas.getDouble("valor"));
					bebida.setImagem(objBebidas.getString("imagem"));

					listBebidas.add(bebida);

				}
				cardapio.setListBebidas(listBebidas);
			} else {
				cardapio.setListBebidas(null);
			}
			estabelecimento.setCardapio(cardapio);
		} else {
			estabelecimento.setCardapio(null);
		}

		if (obj.has("endereco") && !obj.isNull("endereco") && !obj.getJSONObject("endereco").isEmpty()) {
			JSONObject end = obj.getJSONObject("endereco");

			Endereco endereco = new Endereco();
			endereco.setCep(end.getString("cep"));
			endereco.setLocalidade(end.getString("localidade"));
			endereco.setLogradouro(end.getString("logradouro")); // Extrai do objeto Json e converte nos Sets dentro do
																	// Objeto Endereço
			endereco.setPais("Brasil");
			endereco.setUf(end.getString("uf"));
			endereco.setBairro(end.getString("bairro"));
			endereco.setId(end.getInt("id"));
			endereco.setNumero(end.getString("numero"));
			endereco.setComplemento(end.getString("complemento"));
			endereco.setReferencia(end.getString("referencia"));

			estabelecimento.setEndereco(endereco);

		}

		return estabelecimento;
	}

	public static Cliente stringToCliente(String stringsb) {
		Cliente cliente = new Cliente();
		JSONObject json = new JSONObject(stringsb);

		cliente.setIDCliente(json.getInt("idcliente"));
		cliente.setNome(json.getString("nome"));
		cliente.setCpf(json.getString("cpf"));
		cliente.setEmail(json.getString("email"));
		cliente.setEmailIsValid(json.getBoolean("emailIsValid"));
//		cliente.setRg(json.getString("rg"));
		cliente.setSenha(json.getString("senha"));
		cliente.setTelefone(json.getString("telefone"));

		return cliente;

	}

	public static Pedidos stringToPedidos(String stringsb, Cliente cliente, Estabelecimento estabelecimento) {
		JSONObject obj = new JSONObject(stringsb);
		Pedidos pedido = new Pedidos();
		pedido.setIDPedido(obj.getInt("idpedido"));
		pedido.setInstant(Instant.parse(obj.getString("instant")));
		pedido.setCliente(cliente);
		pedido.setEstabelecimento(estabelecimento);

		List<Pratos> listPratos = new ArrayList<>();
		JSONArray pratosArray = obj.getJSONArray("listPratos");
		if (pratosArray.isNull(0) != true) {
			for (int i = 0; i < pratosArray.length(); i++) {
				JSONObject pratojson = pratosArray.getJSONObject(i);
				Pratos newPrato = new Pratos();
				newPrato.setIDPrato(pratojson.getInt("idprato"));
				newPrato.setNome(pratojson.getString("nome"));
				newPrato.setTipoPrato(pratojson.getString("tipoPrato"));
				newPrato.setIngredientes(pratojson.getString("ingredientes"));
				newPrato.setValor(pratojson.getDouble("valor"));
				newPrato.setImagem(pratojson.getString("imagem"));

				listPratos.add(newPrato);
			}
			pedido.setListPratos(listPratos);
		} else {
			pedido.setListPratos(null);
		}

		JSONArray listobjBebidas = obj.getJSONArray("listBebidas");
		if (listobjBebidas.isNull(0) != true) {
			List<Bebidas> listBebidas = new ArrayList<>();
			for (int i = 0; i < listobjBebidas.length(); i++) {
				JSONObject objBebidas = listobjBebidas.getJSONObject(i);
				Bebidas bebida = new Bebidas();
				bebida.setIDBebida(objBebidas.getInt("idbebida"));
				bebida.setNome(objBebidas.getString("nome"));
				bebida.setTipoBebida(objBebidas.getString("tipoBebida"));
				bebida.setValor(objBebidas.getDouble("valor"));
				bebida.setImagem(objBebidas.getString("imagem"));

				listBebidas.add(bebida);

			}
			pedido.setListBebidas(listBebidas);
		} else {
			pedido.setListBebidas(null);
		}

		return pedido;
	}

}

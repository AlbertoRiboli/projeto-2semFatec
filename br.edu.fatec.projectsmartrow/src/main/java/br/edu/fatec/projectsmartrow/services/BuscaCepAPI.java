package br.edu.fatec.projectsmartrow.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import br.edu.fatec.projectsmartrow.exceptions.ExcessaoConexaoDB;
import br.edu.fatec.projectsmartrow.exceptions.ExcessaoHttp;
import br.edu.fatec.projectsmartrow.model.Endereco;
import br.edu.fatec.projectsmartrow.util.ConversorJsonString;

public class BuscaCepAPI {
	
	/*
	 * Classe de função auxiliar para requisitar um cep e obter um retorno de um endereço completo, o mesmo será convertido em String e posteriormente em um
	 * objeto JSON para extração de dados
	 */


	public static String enderecoReq = "http://viacep.com.br/ws/";  //Parte inicial fixa do endereco do site para requisição da API
	public static int resultReq = 200; 								//Variavel para armazenar o código de retorno da requisição

	public static Endereco buscaCep(String cep) {
		String reqUrl = enderecoReq + cep + "/json";				//String montada recebendo o valor adicionado pelo usuário
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != resultReq) {				//Validador da Requisição, sendo 200 para sucesso ou retornando o erro que gerar
				throw new ExcessaoHttp("Erro HTTP :" + conn.getResponseCode());
			}
			BufferedReader response = new BufferedReader(new InputStreamReader((conn.getInputStream()))); //Biblioteca que lê o retorno da requisição e armazena em um Buffere
			String jsonToString = ConversorJsonString.jsonToString(response);			//Variavel que recebe o retorno da função da classe auxiliar JsonToString convertendo o response para String
			JSONObject obj = new JSONObject(jsonToString);			//Biblioteca que recebe e armazena um Json para extração e manipulação dos dados
			Endereco endereco = new Endereco();
			endereco.setCep(cep);
			endereco.setLocalidade(obj.getString("localidade"));
			endereco.setLogradouro(obj.getString("logradouro"));	//Extrai do objeto Json e converte nos Sets dentro do Objeto Endereço
			endereco.setPais("Brasil");
			endereco.setUf(obj.getString("uf"));
			endereco.setBairro(obj.getString("bairro"));
			return endereco;
		} catch (IOException e) {
			throw new ExcessaoConexaoDB(e.getMessage());
		}
	}
	
	public static Endereco atualizaEnderecobuscaCep(String cep, Endereco endereco) {  //A mesma função explicada anteriormente, apenas parametrizada para que atualize um 
		String reqUrl = enderecoReq + cep + "/json";								  // endereço já existente. Recebendo como parâmetro o novo cep e um objeto endereço
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != resultReq) {
				throw new ExcessaoHttp("Erro HTTP :" + conn.getResponseCode());
			}
			BufferedReader response = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String jsonToString = ConversorJsonString.jsonToString(response);
			JSONObject obj = new JSONObject(jsonToString);
			endereco.setCep(cep);
			endereco.setLocalidade(obj.getString("localidade"));
			endereco.setLogradouro(obj.getString("logradouro"));
			endereco.setPais("Brasil");
			endereco.setUf(obj.getString("uf"));
			endereco.setBairro(obj.getString("bairro"));
			return endereco;
		} catch (IOException e) {
			throw new ExcessaoConexaoDB(e.getMessage());
		}
	}
}

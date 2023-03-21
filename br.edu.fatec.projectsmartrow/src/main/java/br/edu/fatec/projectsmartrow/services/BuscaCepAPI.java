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

	public static String enderecoReq = "http://viacep.com.br/ws/";
	public static int resultReq = 200;

	public static Endereco buscaCep(String cep) {
		String reqUrl = enderecoReq + cep + "/json";
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			if (conn.getResponseCode() != resultReq) {
				throw new ExcessaoHttp("Erro HTTP :" + conn.getResponseCode());
			}
			BufferedReader response = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String jsonToString = ConversorJsonString.jsonToString(response);
			JSONObject obj = new JSONObject(jsonToString);
			Endereco endereco = new Endereco();
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

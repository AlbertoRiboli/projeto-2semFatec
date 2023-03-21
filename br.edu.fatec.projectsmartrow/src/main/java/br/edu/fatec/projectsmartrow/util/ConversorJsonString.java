package br.edu.fatec.projectsmartrow.util;

import java.io.BufferedReader;
import java.io.IOException;

import br.edu.fatec.projectsmartrow.exceptions.ExcessaoDadosDeEntradaInvalidos;

public class ConversorJsonString {

	public static String jsonToString(BufferedReader br) {
		String response = "";
		String jsonToString = "";
		try {
			while ((response = br.readLine()) != null) {
				jsonToString += response;
			}
		} catch (IOException e) {
			throw new ExcessaoDadosDeEntradaInvalidos(
					"Erro ao Acessar Método de Conversao JsonToString" + e.getMessage());
		}
		return jsonToString;
	}

}

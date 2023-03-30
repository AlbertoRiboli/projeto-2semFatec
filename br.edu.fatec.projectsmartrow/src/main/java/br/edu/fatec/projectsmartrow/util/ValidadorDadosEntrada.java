package br.edu.fatec.projectsmartrow.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidadorDadosEntrada {

	public boolean validaNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			System.out.println("Atencao! Digite um nome valido!");
			return false;
		}
		return true;
	}

	public boolean validaCnpj(String cnpj) {
		if (cnpj == null || cnpj.isEmpty()) {
			System.out.println("Atencao! Digite um CNPJ valido!");
			return false;
		}
		if (!cnpj.matches("\\d+")) {
			System.out.println("CNPJ Invalido! Digite apenas numeros!");
			return false;
		}
		if (cnpj.length() != 14) {
			System.out.println("CNPJ Invalido, Deve ter 14 digitos! Digite novamente!");
			return false;
		}
		return true;
	}

	public boolean validaTelefone(String telefone) {
		if (telefone == null || telefone.isEmpty()) {
			System.out.println("Telefone invalido! Digite o telefone com DDD");
			return false;
		}
		if (telefone.length() == 10) {
			return true;
		}

		if (telefone.length() == 11) {
			return true;
		}

		System.out.println("Telefone invalido! Digite o telefone com DDD!");

		return false;
	}
	
	public boolean validaEmail(String email) {
		if (email == null || email.isEmpty()) {
			System.out.println("Email Inválido! Digite um email válido! exemplo@exemplo.com");
			return false;
		}
		String emailRegex = "^[A-Za-z0-9+_.-]+@[gmail.com, hotmail.com, outlook.com, yahoo.com]+$";  
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (!matcher.matches()) {
            System.out.println("Email Inválido! Digite um email válido! exemplo@exemplo.com");
            return false;
        }
        return true;
	}
	
	public boolean validaCep(String cep) {
		if (cep == null || cep.isEmpty()) {
			System.out.println("Cep Inválido! Digite um cep válido! Exemplo 00000000");
			return false;
		}
		if (cep.length() != 8) {
			System.out.println("Cep Inválido! Digite um cep válido! Exemplo 00000000");
			return false;
		}
		if (!cep.matches("\\d+")) {
			System.out.println("Cep Inválido! Deve conter apenas números");
			return false;
		}
		return true;
	}
		
	

}

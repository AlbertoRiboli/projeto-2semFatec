package br.edu.fateccotia.projectsmartrow.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;

public class ValidadorDadosEntrada {

	public static boolean validaNome(String nome) {
		if (nome == null || nome.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean validaCnpj(String cnpj) {
		if (cnpj == null || cnpj.isEmpty()) {
			return false;
		}
		if (!cnpj.matches("\\d+")) {
			return false;
		}
		if (cnpj.length() != 14) {
			return false;
		}
		return true;
	}

	public static boolean validaNumero(String numero) {
		if (numero == null || numero.isEmpty()) {
			return false;
		}
		if (!numero.matches("\\d+")) {
			return false;
		}
		return true;
	}

	public static boolean validaCartao(String cartao) {
		if (cartao == null || cartao.isEmpty()) {
			return false;
		}
		if (!cartao.matches("\\d+")) {
			return false;
		}
		if (cartao.length() != 16) {
			return false;
		}
		return true;
	}

	public static boolean validaValidade(String validade) {
		if (validade == null || validade.isEmpty()) {
			return false;
		}
		if (!validade.matches("\\d+")) {
			return false;
		}
		if (validade.length() != 6) {
			return false;
		}
		return true;
	}

	public static boolean validaCodigo(String codigo) {
		if (codigo == null || codigo.isEmpty()) {
			return false;
		}
		if (!codigo.matches("\\d+")) {
			return false;
		}
		if (codigo.length() != 3) {
			return false;
		}
		return true;
	}

	public static boolean validaCpf(String cpf) {
		if (cpf == null || cpf.isEmpty()) {
			return false;
		}
		if (!cpf.matches("\\d+")) {
			return false;
		}
		if (cpf.length() != 11) {
			return false;
		}
		return true;
	}

	public static boolean validaTelefone(String telefone) {
		if (telefone == null || telefone.isEmpty()) {
			return false;
		}
		if (telefone.length() == 10) {
			return true;
		}
		if (telefone.length() == 11) {
			return true;
		}
		return false;
	}

	public static boolean validaEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		String emailregex_confRFC5322 = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		Pattern pattern_email = Pattern.compile(emailregex_confRFC5322);
		Matcher matcher = pattern_email.matcher(email);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	public static boolean validaHorario(String horario) {
		if (horario == null || horario.isEmpty()) {
			return false;
		}
		return true;
	}

	public static boolean validaValor(Double valor) {
		if (valor == null) {
			return false;
		}
		return true;
	}

	public static boolean validaCep(String cep) {
		if (cep == null || cep.isEmpty()) {
			return false;
		}
		if (cep.length() != 8) {
			return false;
		}
		if (!cep.matches("\\d+")) {
			return false;
		}
		return true;
	}

	public static boolean validaValor(String valor) {
		if (valor == null || valor.isEmpty()) {
			return false;
		}
		if (!valor.matches("\\d+(\\.\\d{1,2})?")) {
			return false;
		}
		return true;
	}

}

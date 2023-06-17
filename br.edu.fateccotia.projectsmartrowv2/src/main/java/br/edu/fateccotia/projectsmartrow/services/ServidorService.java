package br.edu.fateccotia.projectsmartrow.services;

import org.springframework.stereotype.Service;

@Service
public class ServidorService {
	
	private static String enderecoServidorWebService = "http://localhost:8080/";
//	private static String enderecoServidorWebService = "http://172.190.33.122:8080/";
	private String email = "richard.silva46fatec@gmail.com";
	private String pass = "rjfyqhrwbpzpzpwy";
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public ServidorService () {}
	
	public ServidorService(String enderecoServidorWebService) {
		this.enderecoServidorWebService = enderecoServidorWebService;
	}


	public static String getEnderecoServidorWebService() {
		return enderecoServidorWebService;
	}

	public void setEnderecoServidorWebService(String enderecoServidorWebService) {
		this.enderecoServidorWebService = enderecoServidorWebService;
	}

	public String enderecoRequest(String request) {
		String newRequest = getEnderecoServidorWebService() + request;
		return newRequest;
	}
	

}

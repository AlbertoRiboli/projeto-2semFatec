package br.edu.fateccotia.projectsmartrow.ServidorS3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import br.edu.fateccotia.projectsmartrow.exceptions.ExcessaoArquivoNaoEncontrado;

public class Credenciais {
	
	public static Properties getProperties() {
		try {
		Properties props = new Properties();
//		FileInputStream file = new FileInputStream(".\\awscredentials.properties");
		InputStream input = Credenciais.class.getClassLoader().getResourceAsStream("awscredentials.properties");
		props.load(input);
		return props;
		} catch (RuntimeException | IOException e) {
			throw new ExcessaoArquivoNaoEncontrado(e.getMessage());
		}
	}
	
	public static String getAcessKey() {
		Properties props = getProperties();
		String acessKey = props.getProperty("acesskey");
		return acessKey;
	}
	
	public static String getSecretKey() {
		Properties props = getProperties();
		String secretKey = props.getProperty("secretkey");
		return secretKey;
	}
	
	public static String getBucket() {
		Properties props = getProperties();
		String bucket = props.getProperty("nomeBucket");
		return bucket;
	}
	
	public static String getDestino() {
		Properties props = getProperties();
		String destino = props.getProperty("destino");
		return destino;
	}

	
	

}

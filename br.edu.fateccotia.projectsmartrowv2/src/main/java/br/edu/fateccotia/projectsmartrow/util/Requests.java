package br.edu.fateccotia.projectsmartrow.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.services.ServidorService;
import javafx.scene.image.Image;

public class Requests {

	private static String endereco = ServidorService.getEnderecoServidorWebService();

	public static Integer POST(JSONObject body, String classe) {
		String enderecoRequest = endereco + classe;
		Integer status = 0;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");

			String jsonToString = body.toString();
			byte[] postData = jsonToString.getBytes(StandardCharsets.UTF_8);
			OutputStream outputStream = conn.getOutputStream();

			outputStream.write(postData);
			outputStream.flush();
			outputStream.close();

			status = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return status;

		} catch (IOException e) {
			e.getMessage();
			return status;
		}
	}

	public static String GET(String classe) {
		String enderecoRequest = ServidorService.getEnderecoServidorWebService() + classe;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();
			return sb.toString();
		} catch (IOException e) {
			e.getMessage();
			return null;
		}

	}

	public static List<Estabelecimento> GETListEstabelecimentos(String classe) {
		List<Estabelecimento> list = new ArrayList<>();
		String enderecoRequest = ServidorService.getEnderecoServidorWebService() + classe;

		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			JSONArray jsonArray = new JSONArray(sb.toString());
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject obj = jsonArray.getJSONObject(i);
				Estabelecimento estabelecimento = new Estabelecimento();
				estabelecimento = JsonInObject.JSONToEstabelecimento(obj);

				list.add(estabelecimento);
			}

		} catch (Exception e) {
			e.getStackTrace();
		}

		return list;

	}

	public static List<Pratos> GETListPratos(String classe) {
		String enderecoRequest = ServidorService.getEnderecoServidorWebService() + classe;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			JSONArray listJason = new JSONArray(sb.toString());
			List<Pratos> list = new ArrayList<>();

			for (int i = 0; i < listJason.length(); i++) {
				JSONObject obj = listJason.getJSONObject(i);
				Pratos prato = new Pratos();

				prato.setIDPrato(obj.getInt("idprato"));
				prato.setNome(obj.getString("nome"));
				prato.setTipoPrato(obj.getString("tipoPrato"));
				prato.setIngredientes(obj.getString("ingredientes"));
				prato.setValor(obj.getDouble("valor"));
				prato.setImagem(obj.getString("imagem"));

				list.add(prato);
			}

			return list;
		} catch (IOException e) {
			e.getMessage();
			return null;
		}

	}

	public static List<Bebidas> GETListBebidas(String classe) {
		String enderecoRequest = ServidorService.getEnderecoServidorWebService() + classe;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			JSONArray listJason = new JSONArray(sb.toString());
			List<Bebidas> list = new ArrayList<>();

			for (int i = 0; i < listJason.length(); i++) {
				JSONObject obj = listJason.getJSONObject(i);
				Bebidas bebida = new Bebidas();

				bebida.setIDBebida(obj.getInt("idbebida"));
				bebida.setNome(obj.getString("nome"));
				bebida.setTipoBebida(obj.getString("tipoBebida"));
				bebida.setValor(obj.getDouble("valor"));
				bebida.setImagem(obj.getString("imagem"));

				list.add(bebida);
			}

			return list;
		} catch (IOException e) {
			e.getMessage();
			return null;
		}

	}

	public static List<Mesas> GETListMesas(String classe) {
		String enderecoRequest = ServidorService.getEnderecoServidorWebService() + classe;
		List<Mesas> list = new ArrayList<>();
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			br.close();

			JSONArray listJason = new JSONArray(sb.toString());
			for (int i = 0; i < listJason.length(); i++) {
				JSONObject obj = listJason.getJSONObject(i);
				Mesas mesa = new Mesas();

				mesa.setIDMesa(obj.getInt("idmesa"));
				mesa.setNumMesa(obj.getInt("numMesa"));
				mesa.setCapacidadePessoas(obj.getInt("capacidadePessoas"));
				mesa.setEnderecoQr(obj.getString("enderecoQr"));
				mesa.setNomeArquivoQr(obj.getString("nomeArquivoQr"));

				list.add(mesa);
			}

			return list;

		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
	}

	public static Integer PUT(JSONObject body, String classe) {
		String enderecoRequest = endereco + classe;
		System.out.println(enderecoRequest);
		Integer status = 0;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("PUT");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");

			String jsonToString = body.toString();
			byte[] postData = jsonToString.getBytes(StandardCharsets.UTF_8);
			OutputStream outputStream = conn.getOutputStream();

			outputStream.write(postData);
			outputStream.flush();
			outputStream.close();

			status = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return status;

		} catch (IOException e) {
			e.getMessage();
			return status;
		}
	}

	public static Integer DELETE(String classe, int id) {
		String enderecoRequest = ServidorService.getEnderecoServidorWebService() + classe + id;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("DELETE");

			Integer code = conn.getResponseCode();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return code;
		} catch (IOException e) {
			ErroJavafx.error("Erro ao fazer DELETE", e.getMessage());
		}

		return null;
	}

	public static String POSTEndereco(String classe) {
		String enderecoRequest = endereco + classe;

		System.out.println("################Request em: " + enderecoRequest);
		Integer status = 0;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");

			status = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return sb.toString();

		} catch (IOException e) {
			e.getMessage();
			return status.toString();
		}
	}

	public static String POSTPedido(String req) {
		String enderecoRequest = endereco + req;

		Integer status = 0;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");

			status = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return sb.toString();

		} catch (IOException e) {
			e.getMessage();
			return status.toString();
		}
	}

	public static String GETPedido(String req) {
		String enderecoRequest = endereco + req;

		Integer status = 0;
		try {
			URL request = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) request.openConnection();
			conn.setRequestMethod("GET");

			status = conn.getResponseCode();
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			br.close();

			return sb.toString();

		} catch (IOException e) {
			e.getMessage();
			return status.toString();
		}
	}

	public static Image GETImage(String request) {
		String enderecoRequest = endereco + "mesas/gerarqrfront/" + request;
		System.out.println("ENDERECO REQUEST QRCODE >>>>>>>>>>>" + enderecoRequest);
		try {
			URL URLrequest = new URL(enderecoRequest);
			HttpURLConnection conn = (HttpURLConnection) URLrequest.openConnection();
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() == 200) {
				InputStream input = conn.getInputStream();
				Image image = new Image(input);

				return image;

			} else {
				ErroJavafx.error("Request Error", "Erro ao receber QrCode do Servidor");
				return null;
			}
		

		} catch (IOException e) {
			ErroJavafx.error("Request Error", "Erro ao receber QrCode do Servidor");
			return null;
		}
	}

}

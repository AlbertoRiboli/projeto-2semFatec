package br.edu.fateccotia.projectsmartrow.view.cliente.screens;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.services.ServidorService;
import br.edu.fateccotia.projectsmartrow.util.JsonInObject;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastrarClienteDadosScreen extends Application {
	
	private static Stage stage;
	
	private static Cliente cliente;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/br/edu/fateccotia/projectsmartrow/view/cliente/fxml/CadastrarClienteDados.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow - Cadastrar Cliente");
			stage.setScene(scene);
			stage.show();
			setStage(stage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		CadastrarClienteDadosScreen.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		CadastrarClienteDadosScreen.cliente = cliente;
	}
	
	public static Integer cadastrarCliente(Cliente cliente, JSONObject endereco) {
		Integer status = 0;
		String enderecoRequest = "clientes";
		JSONObject newCliente = new JSONObject();
		newCliente.put("nome", cliente.getNome());
		newCliente.put("email", cliente.getEmail());
		newCliente.put("senha", cliente.getSenha());
		newCliente.put("rg", cliente.getRg());
		newCliente.put("cpf", cliente.getCpf());
		newCliente.put("telefone", cliente.getTelefone());
		newCliente.put("endereco", endereco);
		
		
		status = Requests.POST(newCliente, enderecoRequest);
		
		return status;
		
	}

}

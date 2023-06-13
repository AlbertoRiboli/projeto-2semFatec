package br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CadastrarEstabelecimentoDadosScreen extends Application {
	
	private static Stage stage;
	
	private static Estabelecimento estabelecimento;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/br/edu/fateccotia/projectsmartrow/view/estabelecimento/fxml/CadastrarEstabelecimentoDados.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow - Cadastrar Estabelecimento");
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
		CadastrarEstabelecimentoDadosScreen.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public static void setEstabelecimento(Estabelecimento estabelecimento) {
		CadastrarEstabelecimentoDadosScreen.estabelecimento = estabelecimento;
	}
	
	public static Integer cadastrarEstabelecimento(Estabelecimento estabelecimento, JSONObject endereco) {
		Integer status = 0;
		String enderecoRequest = "estabelecimentos";
		JSONObject newEst = new JSONObject();
		newEst.put("nome", estabelecimento.getNome());
		newEst.put("email", estabelecimento.getEmail());
		newEst.put("senha", estabelecimento.getSenha());
		newEst.put("cnpj", estabelecimento.getCnpj());
		newEst.put("telefone", estabelecimento.getTelefone());
		newEst.put("telefone2", estabelecimento.getTelefone2());
		newEst.put("endereco", endereco);
		newEst.put("horarioFuncionamento", estabelecimento.getHorarioFuncionamento());
		
		status = Requests.POST(newEst, enderecoRequest);
		
		return status;
		
	}

}

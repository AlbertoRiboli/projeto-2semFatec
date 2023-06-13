package br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuInicialEstabelecimentoScreen extends Application {
	
	private static Stage stage;
	
	private static Estabelecimento estabelecimento = new Estabelecimento();

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/br/edu/fateccotia/projectsmartrow/view/estabelecimento/fxml/MenuInicialEstabelecimento.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow - Estabelecimento");
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
		MenuInicialEstabelecimentoScreen.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public static void setEstabelecimento(Estabelecimento estabelecimento) {
		MenuInicialEstabelecimentoScreen.estabelecimento = estabelecimento;
	}

}

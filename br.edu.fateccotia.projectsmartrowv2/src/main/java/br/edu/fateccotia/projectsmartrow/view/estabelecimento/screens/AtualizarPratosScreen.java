package br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AtualizarPratosScreen extends Application {
	
	private static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/br/edu/fateccotia/projectsmartrow/view/estabelecimento/fxml/AtualizarPratos.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow - Cadastrar Pratos");
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
		AtualizarPratosScreen.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

			

}

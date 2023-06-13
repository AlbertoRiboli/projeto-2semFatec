package br.edu.fateccotia.projectsmartrow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicialJavafx extends Application {

	private static Stage stage;

	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/br/edu/fateccotia/projectsmartrow/view/login/login.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow - Login");
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
		InicialJavafx.stage = stage;
	}
	
	public static void fecharStage() {
		getStage().close();
	}
	
	

	public static void main(String[] args) {
		launch(args);
	}

}

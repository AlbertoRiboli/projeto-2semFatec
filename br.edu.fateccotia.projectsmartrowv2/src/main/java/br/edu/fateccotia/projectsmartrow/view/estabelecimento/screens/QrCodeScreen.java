package br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QrCodeScreen extends Application {
	
	private static Stage stage;
	
	private static Mesas mesa;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/br/edu/fateccotia/projectsmartrow/view/estabelecimento/fxml/QrCode.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("Project SmartRow - Gerar QrCode");
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
		QrCodeScreen.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Mesas getMesa() {
		return mesa;
	}

	public static void setMesa(Mesas mesa) {
		QrCodeScreen.mesa = mesa;
	}

			

}

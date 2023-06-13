package br.edu.fateccotia.projectsmartrow.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErroJavafx {
	
	public static void error(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(titulo);
		alert.setContentText(mensagem);
		alert.showAndWait();
		
	}
	
	public static void information(String titulo, String mensagem) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(titulo);
		alert.setContentText(mensagem);
		alert.showAndWait();
		
	}
	
	public static void funcaoNaoImplementada() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Função Não Implementada");
		alert.setTitle("Atenção");
		alert.setContentText("A função requisitada ainda não foi implementada. "
				+ "Estamos em desenvolvimento. Obrigado pela compreensão!");
		alert.showAndWait();
	}
	

}

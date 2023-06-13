package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.view.cliente.screens.ConfirmacaoPagamentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmacaoPagamentoViewController implements Initializable{


    @FXML
    private Button btnFinalizar;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
			
		btnFinalizar.setOnMouseClicked(event ->{
			MenuInicialClienteScreen inicial = new MenuInicialClienteScreen();
			ConfirmacaoPagamentoScreen.getStage().close();
			try {
				inicial.start(new Stage());	
				
			}
			catch(Exception e){
				e.getStackTrace();
			}
			
		});
		
	
		
	}

	

}

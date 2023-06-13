package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.InicialJavafx;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteConfirmacaoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteDadosScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastrarClienteConfirmacaoViewController implements Initializable{

    @FXML
    private Button btnFinalizar;

    @FXML
    private Label labelNome;
	
    @FXML
	private ImageView btEncerrar;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelNome.setText("Olá " + CadastrarClienteDadosScreen.getCliente().getNome() + ",");
		
			
		btnFinalizar.setOnMouseClicked(event ->{
			InicialJavafx inicial = new InicialJavafx();
			CadastrarClienteConfirmacaoScreen.getStage().close();
			try {
				inicial.start(new Stage());	
				
			}
			catch(Exception e){
				e.getStackTrace();
			}
			
		});
		
		btEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
		
	}

	

}

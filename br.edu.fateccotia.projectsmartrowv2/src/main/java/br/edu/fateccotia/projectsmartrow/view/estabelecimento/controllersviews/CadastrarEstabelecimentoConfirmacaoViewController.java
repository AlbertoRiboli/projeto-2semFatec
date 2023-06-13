package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.InicialJavafx;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoConfirmacaoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoDadosScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastrarEstabelecimentoConfirmacaoViewController implements Initializable{

    @FXML
    private Button btnFinalizar;

    @FXML
    private Label labelNome;
	
    @FXML
	private ImageView btEncerrar;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String nome = null;
		if (CadastrarEstabelecimentoDadosScreen.getEstabelecimento() == null) {
			nome = "null";
		}
		else {
			nome = CadastrarEstabelecimentoDadosScreen.getEstabelecimento().getNome();
		}
		
		labelNome.setText("Olá " + nome + ",");
		
			
		btnFinalizar.setOnMouseClicked(event ->{
			InicialJavafx inicial = new InicialJavafx();
			CadastrarEstabelecimentoConfirmacaoScreen.getStage().close();
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

package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteScreenEmailSenha;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastrarClienteEmailSenhaViewController implements Initializable {


    @FXML
    private Button btnProximo;

    @FXML
    private Label labelStatus;

    @FXML
    private PasswordField textConfirmaSenha;

    @FXML
    private TextField textEmail;

    @FXML
    private PasswordField textSenha;
    
    @FXML
	private ImageView btEncerrar;

	boolean emailIsValid = false;
	boolean senhaIsValid = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnProximo.setOnMouseClicked((MouseEvent e) -> {
			if (ValidadorDadosEntrada.validaEmail(textEmail.getText()) == true) {

				String exists = Requests.GET("clientes/email/" + textEmail.getText());
				if (exists == null) {
					String exists2 = Requests.GET("estabelecimentos/email/" + textEmail.getText());
					if(exists2 == null) {
					emailIsValid = true;
					}
					else {
						labelStatus.setText("Email já cadastrado para um Estabelecimento!");
					}
				} else {
					labelStatus.setText("Email já cadastrado!");
				}
			} else {
				labelStatus.setText("Digite um email válido!");
			}
			
			if (!textSenha.getText().isEmpty()) {
				if(textSenha.getText().equals(textConfirmaSenha.getText())) {
					senhaIsValid = true;
					}
				else {
					labelStatus.setText("Senhas não conferem");
				}
			}
			else {
				labelStatus.setText("Digite uma senha");
			}
			
			if(emailIsValid == true && senhaIsValid == true) {
				CadastrarClienteDadosScreen cadastrarDados = new CadastrarClienteDadosScreen();
				CadastrarClienteScreenEmailSenha.getStage().close();
				
				Cliente cliente = new Cliente();
				cliente.setEmail(textEmail.getText());
				cliente.setSenha(textSenha.getText());
				CadastrarClienteDadosScreen.setCliente(cliente);
				try {
					cadastrarDados.start(new Stage());
				}
				catch(Exception e1) {
					e1.printStackTrace();
					
				}
			}
			
		}
		
		

		);
		
		btEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
	}

}

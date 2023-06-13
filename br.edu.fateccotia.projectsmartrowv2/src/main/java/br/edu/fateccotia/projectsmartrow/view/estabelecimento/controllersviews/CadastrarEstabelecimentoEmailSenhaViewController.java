package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoEmailSenhaScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastrarEstabelecimentoEmailSenhaViewController implements Initializable {

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

				String exists = Requests.GET("estabelecimentos/email/" + textEmail.getText());
				if (exists == null) {
					String exists2 = Requests.GET("clientes/email/" + textEmail.getText());
					if (exists2 == null) {
						emailIsValid = true;
					}
					else{
						labelStatus.setText("Existe Cliente com Email já cadastrado!");
					}
				} else {
					labelStatus.setText("Email já cadastrado!");
				}
			} else {
				labelStatus.setText("Digite um email válido!");
			}

			if (!textSenha.getText().isEmpty()) {
				if (textSenha.getText().equals(textConfirmaSenha.getText())) {
					senhaIsValid = true;
				} else {
					labelStatus.setText("Senhas não conferem");
				}
			} else {
				labelStatus.setText("Digite uma senha");
			}

			if (emailIsValid == true && senhaIsValid == true) {
				CadastrarEstabelecimentoDadosScreen cadastrarDados = new CadastrarEstabelecimentoDadosScreen();
				CadastrarEstabelecimentoEmailSenhaScreen.getStage().close();

				Estabelecimento estabelecimento = new Estabelecimento();
				estabelecimento.setEmail(textEmail.getText());
				estabelecimento.setSenha(textSenha.getText());
				CadastrarEstabelecimentoDadosScreen.setEstabelecimento(estabelecimento);
				try {
					cadastrarDados.start(new Stage());
				} catch (Exception e1) {
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

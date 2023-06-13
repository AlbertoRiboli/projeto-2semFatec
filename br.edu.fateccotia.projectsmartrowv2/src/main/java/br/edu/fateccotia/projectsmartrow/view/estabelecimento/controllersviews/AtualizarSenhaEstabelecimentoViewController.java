package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.JsonInObject;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarSenhaEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class AtualizarSenhaEstabelecimentoViewController implements Initializable {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Label labelStatus;

	@FXML
	private PasswordField textConfSenha;

	@FXML
	private PasswordField textNewSenha;

	@FXML
	private PasswordField textSenha;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnConfirmar.setOnMouseClicked(event -> {
			labelStatus.setText("");

			if (!textSenha.getText().equals("")) {
				JSONObject obj2 = new JSONObject();
				obj2.put("email", MenuInicialEstabelecimentoScreen.getEstabelecimento().getEmail());
				obj2.put("senha", textSenha.getText());
				String enderecoRequest = "autenticar/validarlogin";
				int code = Requests.POST(obj2, enderecoRequest);

				if (code == 200) {

					if (!textNewSenha.getText().equals("") && !textConfSenha.getText().equals("")) {
						boolean checkSenha = textNewSenha.getText().equals(textConfSenha.getText());
						if (checkSenha == true) {
							JSONObject obj = new JSONObject();
							obj.put("senha", textNewSenha.getText());
							Requests.PUT(obj, "estabelecimentos/atualizarsenha/"
									+ MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento());
							String retorno = Requests.GET("estabelecimentos/email/"
									+ MenuInicialEstabelecimentoScreen.getEstabelecimento().getEmail());
							MenuInicialEstabelecimentoScreen
									.setEstabelecimento(JsonInObject.stringToEstabelecimento(retorno));

							ErroJavafx.information("Concluído!", "A senha foi alterada com sucesso!");
							AtualizarSenhaEstabelecimentoScreen.getStage().close();
						}
						else {
							labelStatus.setText("Senhas não conferem!");
						}
					}
					else {
						labelStatus.setText("Digite a nova senha!");
					}
				}
				else {
					labelStatus.setText("Senha atual inválida!");
				}
			} else {
				labelStatus.setText("Digite a senha atual");
			}
		});

		btnCancelar.setOnMouseClicked(event -> {
			AtualizarSenhaEstabelecimentoScreen.getStage().close();
		});

	}

}

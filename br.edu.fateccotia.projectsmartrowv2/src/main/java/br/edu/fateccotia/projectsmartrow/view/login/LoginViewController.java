package br.edu.fateccotia.projectsmartrow.view.login;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.InicialJavafx;
import br.edu.fateccotia.projectsmartrow.util.JsonInObject;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteScreenEmailSenha;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoEmailSenhaScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {

	@FXML
	private Hyperlink linkCadastrarEstabelecimento;

	@FXML
	private Hyperlink linkCadastrarUsuario;

	@FXML
	private TextField textEmail;

	@FXML
	private PasswordField textSenha;

	@FXML
	private Button btnEntrar;

	@FXML
	private Label labelStatus;

	@FXML
	private ImageView btEncerrar;

	public Hyperlink getLinkCadastrarEstabelecimento() {
		return linkCadastrarEstabelecimento;
	}

	public void setLinkCadastrarEstabelecimento(Hyperlink linkCadastrarEstabelecimento) {
		this.linkCadastrarEstabelecimento = linkCadastrarEstabelecimento;
	}

	public Hyperlink getLinkCadastrarUsuario() {
		return linkCadastrarUsuario;
	}

	public void setLinkCadastrarUsuario(Hyperlink linkCadastrarUsuario) {
		this.linkCadastrarUsuario = linkCadastrarUsuario;
	}

	public TextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(TextField textEmail) {
		this.textEmail = textEmail;
	}

	public PasswordField getTextSenha() {
		return textSenha;
	}

	public void setTextSenha(PasswordField textSenha) {
		this.textSenha = textSenha;
	}

	public void fazerLogin() {
		JSONObject obj = new JSONObject();
		obj.put("email", textEmail.getText());
		obj.put("senha", textSenha.getText());
		String enderecoRequest = "autenticar/validarlogin";

		Requests.POST(obj, enderecoRequest);
		int code = 0;
		code = Requests.POST(obj, enderecoRequest);
		System.out.println(code);

		if (code == 200) {
			String retornoBusca = Requests.GET("clientes/email/" + textEmail.getText());
			if (retornoBusca != null) {
				MenuInicialClienteScreen menuCliente = new MenuInicialClienteScreen();
				MenuInicialClienteScreen.setCliente(JsonInObject.stringToCliente(retornoBusca));
				InicialJavafx.getStage().close();

				try {
					menuCliente.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				retornoBusca = Requests.GET("estabelecimentos/email/" + textEmail.getText());
				if (retornoBusca != null) {
					System.out.println(retornoBusca);
					MenuInicialEstabelecimentoScreen menuEstabelecimento = new MenuInicialEstabelecimentoScreen();
					MenuInicialEstabelecimentoScreen
							.setEstabelecimento(JsonInObject.stringToEstabelecimento(retornoBusca));

					InicialJavafx.getStage().close();
					try {
						menuEstabelecimento.start(new Stage());
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
			}

		} else if (code == 401) {
			labelStatus.setText("Usuário ou Senha Inválidos");
		} else if (code == 404) {
			labelStatus.setText("Usuário ou Senha Inválidos");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnEntrar.setOnMouseClicked((MouseEvent e) -> {
			fazerLogin();
		});

		btnEntrar.setOnKeyPressed((KeyEvent e) -> {
			if (e.getCode() == KeyCode.ENTER) {
				fazerLogin();
			}
		});

		linkCadastrarUsuario.setOnMouseClicked((MouseEvent e) -> {
			CadastrarClienteScreenEmailSenha cadastrarCliente = new CadastrarClienteScreenEmailSenha();
			InicialJavafx.fecharStage();
			try {
				cadastrarCliente.start(new Stage());

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		linkCadastrarEstabelecimento.setOnMouseClicked((MouseEvent e) -> {
			CadastrarEstabelecimentoEmailSenhaScreen cadastrarCliente = new CadastrarEstabelecimentoEmailSenhaScreen();
			InicialJavafx.fecharStage();
			try {
				cadastrarCliente.start(new Stage());

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});

		btEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

	}

}

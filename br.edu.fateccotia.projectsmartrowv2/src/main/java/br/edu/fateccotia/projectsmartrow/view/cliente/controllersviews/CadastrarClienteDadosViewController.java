package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Cliente;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteEnderecoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastrarClienteDadosViewController implements Initializable {

	@FXML
	private TextField textCpf;

	@FXML
	private TextField textTelefone;

	@FXML
	private Button btnProximo;

	@FXML
	private Label labelStatus;

	@FXML
	private TextField textNome;

	@FXML
	private TextField textRg;

	@FXML
	private ImageView btEncerrar;

	private boolean nomeIsValid = false;
	private boolean cpfIsValid = false;
	private boolean telefoneIsValid = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnProximo.setOnMouseClicked((MouseEvent e) -> {
			Cliente cliente = CadastrarClienteDadosScreen.getCliente();

			if (ValidadorDadosEntrada.validaNome(textNome.getText()) == true) {
				nomeIsValid = true;
			} else {
				labelStatus.setText("nome inválido");
			}
			if (ValidadorDadosEntrada.validaCpf(textCpf.getText()) == true) {
				String buscarCliente = Requests.GET("clientes/cpf/" + textCpf.getText());
				if (buscarCliente == null) {
					cpfIsValid = true;
				} else {
					labelStatus.setText("CPF já utilizado");
				}
			} else {
				labelStatus.setText("CPF inválido");
			}
			if (ValidadorDadosEntrada.validaTelefone(textTelefone.getText()) == true) {
				telefoneIsValid = true;
			} else {
				labelStatus.setText("Telefone Inválido");
			}

			if (nomeIsValid == true && cpfIsValid == true && telefoneIsValid == true) {
				CadastrarClienteDadosScreen.getCliente().setCpf(textCpf.getText());
				CadastrarClienteDadosScreen.getCliente().setNome(textNome.getText());
				CadastrarClienteDadosScreen.getCliente().setTelefone(textTelefone.getText());
				if (textRg.getText().isEmpty()) {
					CadastrarClienteDadosScreen.getCliente().setRg(null);
				} else {
					CadastrarClienteDadosScreen.getCliente().setRg(textRg.getText());
				}

				CadastrarClienteEnderecoScreen cadastrarEndereco = new CadastrarClienteEnderecoScreen();
				CadastrarClienteDadosScreen.getStage().close();
				System.out.println(CadastrarClienteDadosScreen.getCliente());
				try {
					cadastrarEndereco.start(new Stage());
				} catch (Exception e1) {
					e1.getStackTrace();
				}

			}

		});

		btEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
	}

}

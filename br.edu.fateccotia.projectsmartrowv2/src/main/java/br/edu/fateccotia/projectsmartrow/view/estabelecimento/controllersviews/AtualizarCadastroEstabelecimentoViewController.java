package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarCadastroEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarSenhaEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarPratosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.PratosEstabelecimentoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AtualizarCadastroEstabelecimentoViewController implements Initializable {

	@FXML
	private Button btnAlterarImagem;

	@FXML
	private Button btnAlterarSenha;

	@FXML
	private ImageView btnChat;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnProximo;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelStatus;

	@FXML
	private TextField textCnpj;

	@FXML
	private TextField textEmail;

	@FXML
	private TextField textHorarioFuncionamento;

	@FXML
	private TextField textNome;

	@FXML
	private TextField textTelefone;

	@FXML
	private TextField textTelefone2;

	private boolean nomeIsValid = false;
	private boolean cnpjIsValid = false;
	private boolean telefoneIsValid = false;
	private boolean telefone2IsValid = false;
	private boolean emailIsValid = false;
	private boolean horarioIsValid = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Estabelecimento estabelecimento = MenuInicialEstabelecimentoScreen.getEstabelecimento();
		textNome.setText(estabelecimento.getNome());
		textCnpj.setText(estabelecimento.getCnpj());
		textTelefone.setText(estabelecimento.getTelefone());
		textTelefone2.setText(estabelecimento.getTelefone2());
		textEmail.setText(estabelecimento.getEmail());
		textHorarioFuncionamento.setText(estabelecimento.getHorarioFuncionamento());

		btnProximo.setOnMouseClicked((MouseEvent e) -> {

			if (ValidadorDadosEntrada.validaNome(textNome.getText()) == true) {
				estabelecimento.setNome(textNome.getText());
				nomeIsValid = true;

			} else {
				labelStatus.setText("O Estabelecimento deve conter nome");
			}

			if (ValidadorDadosEntrada.validaCnpj(textCnpj.getText()) == true) {
				estabelecimento.setCnpj(textCnpj.getText());
				cnpjIsValid = true;
			} else {
				labelStatus.setText("O Estabelecimento deve conter cnpj válido. 14 digitos e somente números!");
			}

			if (ValidadorDadosEntrada.validaTelefone(textTelefone.getText()) == true) {
				estabelecimento.setTelefone(textTelefone.getText());
				telefoneIsValid = true;
			} else {
				labelStatus.setText("Os telefones devem ser válidos! Apenas números, DDD+Número");
			}

			if (ValidadorDadosEntrada.validaTelefone(textTelefone2.getText()) == true) {
				estabelecimento.setTelefone2(textTelefone2.getText());
				telefone2IsValid = true;
			} else {
				labelStatus.setText("Os telefones devem ser válidos! Apenas números, DDD+Número");
			}

			if (ValidadorDadosEntrada.validaEmail(textEmail.getText()) == true) {
				estabelecimento.setEmail(textEmail.getText());
				emailIsValid = true;
			} else {
				labelStatus.setText("Email inválido");
			}

			if (ValidadorDadosEntrada.validaNome(textHorarioFuncionamento.getText()) == true) {
				estabelecimento.setHorarioFuncionamento(textHorarioFuncionamento.getText());
				horarioIsValid = true;
			} else {
				labelStatus.setText("O estabelecimento deve conter horário de funcionamento");
			}

			if (nomeIsValid == true && cnpjIsValid == true && telefoneIsValid == true && telefone2IsValid == true
					&& emailIsValid == true && horarioIsValid == true) {

				Integer status = atualizarEstabelecimento(estabelecimento);

				if (status == 200) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Operação Realizada com Sucesso!");
					alert.setHeaderText("Operação Realizada com Sucesso!");
					alert.setContentText("O cadastro foi atualizado com sucesso!");
					alert.showAndWait();

					MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
					MenuInicialEstabelecimentoScreen menu = new MenuInicialEstabelecimentoScreen();
					AtualizarCadastroEstabelecimentoScreen.getStage().close();
					try {
						menu.start(new Stage());
					} catch (Exception e1) {
						e1.getStackTrace();
					}

				} else {
					labelStatus.setText("Erro ao atualizar o cadastro!");
				}

			}

		});

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

		btnVoltar.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoScreen menu = new MenuInicialEstabelecimentoScreen();
			AtualizarCadastroEstabelecimentoScreen.getStage().close();
			try {
				menu.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnChat.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoViewController.funcaoNaoImplementada();
		});

		btnAlterarImagem.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoViewController.funcaoNaoImplementada();
		});
		
		btnAlterarSenha.setOnMouseClicked(event ->{
			AtualizarSenhaEstabelecimentoScreen atualizarSenha= new AtualizarSenhaEstabelecimentoScreen();
			try {
				atualizarSenha.start(new Stage());
			}
			catch(Exception senha) {
				senha.getStackTrace();
			}
		});
	}

	public Integer atualizarEstabelecimento(Estabelecimento estabelecimento) {
		Integer status = 0;
		String enderecoRequest = "estabelecimentos/"
				+ MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento();
		JSONObject obj = new JSONObject();
		obj.put("nome", estabelecimento.getNome());
		obj.put("cnpj", estabelecimento.getCnpj());
		obj.put("telefone", estabelecimento.getTelefone());
		obj.put("telefone2", estabelecimento.getTelefone2());
		obj.put("email", estabelecimento.getEmail());
		obj.put("horarioFuncionamento", estabelecimento.getHorarioFuncionamento());

		status = Requests.PUT(obj, enderecoRequest);

		return status;

	}

}

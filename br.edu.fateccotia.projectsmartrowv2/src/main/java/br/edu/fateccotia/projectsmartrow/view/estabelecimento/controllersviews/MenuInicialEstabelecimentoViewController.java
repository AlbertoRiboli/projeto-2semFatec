package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.InicialJavafx;
import br.edu.fateccotia.projectsmartrow.util.JsonInObject;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarCadastroEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.BebidasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MesasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.PratosEstabelecimentoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuInicialEstabelecimentoViewController implements Initializable {
	@FXML
	private Button btnAtualizarCadastro;

	@FXML
	private Button btnBebidas;

	@FXML
	private ImageView btnChat;

	@FXML
	private Button btnConfiguracoes;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnMesas;

	@FXML
	private Button btnMesasAtendimento;

	@FXML
	private Button btnPedidosRecebidos;

	@FXML
	private Button btnPratos;

	@FXML
	private Button btnPromocoes;

	@FXML
	private Button btnRelatorioVendas;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelDadosEstabelecimento;

	@FXML
	private Label labelEstabelecimento;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		String nome = null;
		if (MenuInicialEstabelecimentoScreen.getEstabelecimento() == null) {
			nome = "null";
		} else {
			nome = MenuInicialEstabelecimentoScreen.getEstabelecimento().getNome();
		}
		labelEstabelecimento.setText(nome);
		String dados = null;
		if (MenuInicialEstabelecimentoScreen.getEstabelecimento().getEndereco() == null) {
			dados = "null";
		} else {
			dados = MenuInicialEstabelecimentoScreen.getEstabelecimento().getEndereco().exibirEnderecoPerfil();
		}
		labelDadosEstabelecimento.setText(dados);

		btnRelatorioVendas.setOnMouseClicked(event -> {
			funcaoNaoImplementada();
		});

		btnPratos.setOnMouseClicked(event -> {
			PratosEstabelecimentoScreen pratosEstabelecimento = new PratosEstabelecimentoScreen();
			MenuInicialEstabelecimentoScreen.getStage().close();
			try {
				pratosEstabelecimento.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnBebidas.setOnMouseClicked(event -> {
			BebidasEstabelecimentoScreen bebidasEstabelecimento = new BebidasEstabelecimentoScreen();
			MenuInicialEstabelecimentoScreen.getStage().close();
			try {
				bebidasEstabelecimento.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnMesas.setOnMouseClicked(event -> {
			MesasEstabelecimentoScreen mesasEstabelecimento = new MesasEstabelecimentoScreen();
			MenuInicialEstabelecimentoScreen.getStage().close();
			try {
				mesasEstabelecimento.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnAtualizarCadastro.setOnMouseClicked(event -> {
			AtualizarCadastroEstabelecimentoScreen atualizar = new AtualizarCadastroEstabelecimentoScreen();
			MenuInicialEstabelecimentoScreen.getStage().close();
			try {
				atualizar.start(new Stage());
			}
			catch(Exception e) {
				e.getStackTrace();
			}
		});

		btnConfiguracoes.setOnMouseClicked(event -> {
			funcaoNaoImplementada();
		});

		btnPromocoes.setOnMouseClicked(event -> {
			funcaoNaoImplementada();
		});

		btnPedidosRecebidos.setOnMouseClicked(event -> {
			funcaoNaoImplementada();
		});

		btnMesasAtendimento.setOnMouseClicked(event -> {
			funcaoNaoImplementada();
		});

		btnChat.setOnMouseClicked(event -> {
			funcaoNaoImplementada();
		});

		btnVoltar.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoScreen.getStage().close();
			MenuInicialEstabelecimentoScreen.setEstabelecimento(null);
			InicialJavafx inicial = new InicialJavafx();
			try {
				inicial.start(new Stage());
			}
			catch (Exception e) {
				e.getStackTrace();
			}
			
			
		});

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

	}

	public static void funcaoNaoImplementada() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Função Não Implementada");
		alert.setTitle("Atenção");
		alert.setContentText("A função requisitada ainda não foi implementada. "
				+ "Estamos em desenvolvimento. Obrigado pela compreensão!");
		alert.showAndWait();
	}

	public static void atualizarEstabelecimentoServidor() {
		String retorno = Requests
				.GET("estabelecimentos/email/" + MenuInicialEstabelecimentoScreen.getEstabelecimento().getEmail());
		MenuInicialEstabelecimentoScreen.setEstabelecimento(JsonInObject.stringToEstabelecimento(retorno));
	}

}

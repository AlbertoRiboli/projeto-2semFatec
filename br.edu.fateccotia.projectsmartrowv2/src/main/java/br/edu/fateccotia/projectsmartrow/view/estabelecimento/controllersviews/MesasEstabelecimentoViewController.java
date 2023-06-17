package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarMesasScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarMesasScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MesasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.QrCodeScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MesasEstabelecimentoViewController implements Initializable {

	@FXML
	private Button btnAdicionar;

	@FXML
	private Button btnAtualizar;

	@FXML
	private ImageView btnChat;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnGerarQr;

	@FXML
	private Button btnMesas;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelCheckList;

	@FXML
	private Label labelDadosEstabelecimento;

	@FXML
	private Label labelEstabelecimento;

	@FXML
	private ListView<Mesas> listMesas;

	private ObservableList<Mesas> listMesasObservable;

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

		if (MenuInicialEstabelecimentoScreen.getEstabelecimento().getMesas() != null) {
			List<Mesas> buscarListaMesas = new ArrayList<>();
			buscarListaMesas = Requests.GETListMesas("mesas/listarmesas/"
					+ MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento());
			listMesasObservable = FXCollections.observableArrayList(buscarListaMesas);
			listMesas.setItems(listMesasObservable);

		} else {
			labelCheckList.setText("Não existe Mesas cadastrados!");
		}

		btnVoltar.setOnMouseClicked(event ->

		{
			MenuInicialEstabelecimentoScreen menuEstabelecimento = new MenuInicialEstabelecimentoScreen();
			MesasEstabelecimentoScreen.getStage().close();
			try {
				menuEstabelecimento.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

		btnAdicionar.setOnMouseClicked(event -> {
			CadastrarMesasScreen cadastrarMesas = new CadastrarMesasScreen();
			MesasEstabelecimentoScreen.getStage().close();
			try {
				cadastrarMesas.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnExcluir.setOnMouseClicked(event -> {
			Mesas mesa = listMesas.getSelectionModel().getSelectedItem();
			if (mesa != null) {
				Requests.DELETE("mesas/", mesa.getIDMesa());
				listMesasObservable.remove(mesa);
				MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
				listMesas.refresh();
			} else {
				ErroJavafx.error("Item não selecionado",
						"'Para excluir um item, antes deve seleciona-lo na lista para depois excluir!");
			}
		});

		btnAtualizar.setOnMouseClicked(event -> {
			Mesas mesa = listMesas.getSelectionModel().getSelectedItem();
			if (mesa != null) {
				AtualizarMesasViewController.setMesa(mesa);
				AtualizarMesasScreen atualizar = new AtualizarMesasScreen();
				MesasEstabelecimentoScreen.getStage().close();
				try {
					atualizar.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				ErroJavafx.error("Item não selecionado",
						"'Para atualizar um item, antes deve seleciona-lo na lista para depois atualizar!");
			}
		});

		btnChat.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnGerarQr.setOnMouseClicked(event -> {
			Mesas mesa = listMesas.getSelectionModel().getSelectedItem();
			if (mesa != null) {
				QrCodeScreen.setMesa(mesa);
				Image image = Requests.GETImage(mesa.getNomeArquivoQr());
				if (image != null) {
					QrCodeScreen qrcode = new QrCodeScreen();
					try {
						qrcode.start(new Stage());
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
				else {
					ErroJavafx.error("QRCodeInválido", "O QrCode requisitado é inválido ou não existe");
				}
			} else {
				ErroJavafx.error("Item não selecionado", "'Para gerar um qr code, antes deve selecionar uma mesa!");
			}

		});

	}

	public void funcaoNaoImplementada() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Função Não Implementada");
		alert.setTitle("Atenção");
		alert.setContentText("A função requisitada ainda não foi implementada. "
				+ "Estamos em desenvolvimento. Obrigado pela compreensão!");
		alert.showAndWait();
	}

}

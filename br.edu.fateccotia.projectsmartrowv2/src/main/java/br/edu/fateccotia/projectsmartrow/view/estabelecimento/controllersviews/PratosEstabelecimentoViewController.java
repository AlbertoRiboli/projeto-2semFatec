package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarPratosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarPratosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.PratosEstabelecimentoScreen;
import ch.qos.logback.core.net.SyslogOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PratosEstabelecimentoViewController implements Initializable {

	@FXML
	private Label labelCheckList;

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
	private Button btnPratos;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelDadosEstabelecimento;

	@FXML
	private Label labelEstabelecimento;

	@FXML
	private ListView<Pratos> listProdutos;

	private ObservableList<Pratos> listProdutosObservable;

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

		if (MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio() != null) {
			if (MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio().getPratos() != null) {
				List<Pratos> buscarListaPratos = new ArrayList<>();
				buscarListaPratos = Requests.GETListPratos("pratos/listarpratos/"
						+ MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento());
				listProdutosObservable = FXCollections.observableArrayList(buscarListaPratos);
				listProdutos.setItems(listProdutosObservable);
				System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio().getPratos());
				System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio().getBebidas());
			} else {
				labelCheckList.setText("Não existe Pratos cadastrados!");
			}
		} else {
			labelCheckList.setText("Não existe Pratos cadastrados!");
		}

		btnVoltar.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoScreen menuEstabelecimento = new MenuInicialEstabelecimentoScreen();
			PratosEstabelecimentoScreen.getStage().close();
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
			CadastrarPratosScreen cadastrarPratos = new CadastrarPratosScreen();
			PratosEstabelecimentoScreen.getStage().close();
			try {
				cadastrarPratos.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnExcluir.setOnMouseClicked(event -> {
			Pratos prato = listProdutos.getSelectionModel().getSelectedItem();
			if (prato != null) {
				Requests.DELETE("pratos/", prato.getIDPrato());
				listProdutosObservable.remove(prato);
				MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
				listProdutos.refresh();
			} else {
				ErroJavafx.error("Item não selecionado",
						"'Para excluir um item, antes deve seleciona-lo na lista para depois excluir!");
			}
		});

		btnAtualizar.setOnMouseClicked(event -> {
			Pratos prato = listProdutos.getSelectionModel().getSelectedItem();
			if (prato != null) {
				AtualizarPratosViewController.setPrato(prato);
				AtualizarPratosScreen atualizar = new AtualizarPratosScreen();
				PratosEstabelecimentoScreen.getStage().close();
				try {
					atualizar.start(new Stage());
				}
				catch(Exception e) {
					e.getStackTrace();
				}
			} else {
				ErroJavafx.error("Item não selecionado",
						"'Para atualizar um item, antes deve seleciona-lo na lista para depois atualizar!");
			}
		});
		
		btnChat.setOnMouseClicked(event ->{
			ErroJavafx.funcaoNaoImplementada();
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

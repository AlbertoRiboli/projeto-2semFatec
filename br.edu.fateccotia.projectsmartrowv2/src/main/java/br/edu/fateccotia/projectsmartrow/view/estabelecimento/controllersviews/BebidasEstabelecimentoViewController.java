package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarBebidasScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.BebidasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarBebidasScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
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

public class BebidasEstabelecimentoViewController implements Initializable {

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
	private ListView<Bebidas> listProdutos;

	private ObservableList<Bebidas> listProdutosObservable;

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
			if (MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio().getBebidas() != null) {
				List<Bebidas> buscarListaBebidas = new ArrayList<>();
				buscarListaBebidas = Requests.GETListBebidas("bebidas/listarbebidas/"
						+ MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento());
				listProdutosObservable = FXCollections.observableArrayList(buscarListaBebidas);
				listProdutos.setItems(listProdutosObservable);
				System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio().getPratos());
				System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento().getCardapio().getBebidas());
			} else {
				labelCheckList.setText("Não existe Bebidas cadastrados!");
			}
		} else {
			labelCheckList.setText("Não existe Bebidas cadastrados!");
		}

		btnVoltar.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoScreen menuEstabelecimento = new MenuInicialEstabelecimentoScreen();
			BebidasEstabelecimentoScreen.getStage().close();
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
			CadastrarBebidasScreen cadastrarBebidas = new CadastrarBebidasScreen();
			BebidasEstabelecimentoScreen.getStage().close();
			try {
				cadastrarBebidas.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnExcluir.setOnMouseClicked(event -> {
			Bebidas	bebida = listProdutos.getSelectionModel().getSelectedItem();
			if (bebida != null) {
				Requests.DELETE("bebidas/", bebida.getIDBebida());
				listProdutosObservable.remove(bebida);
				MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
				listProdutos.refresh();
			} else {
				ErroJavafx.error("Item não selecionado",
						"'Para excluir um item, antes deve seleciona-lo na lista para depois excluir!");
			}
		});

		btnAtualizar.setOnMouseClicked(event -> {
			Bebidas bebida = listProdutos.getSelectionModel().getSelectedItem();
			if (bebida != null) {
				AtualizarBebidasViewController.setBebida(bebida);
				AtualizarBebidasScreen atualizar = new AtualizarBebidasScreen();
				BebidasEstabelecimentoScreen.getStage().close();
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

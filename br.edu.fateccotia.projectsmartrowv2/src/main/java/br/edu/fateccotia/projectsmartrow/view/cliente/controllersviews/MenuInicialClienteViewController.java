package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.InicialJavafx;
import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.SelecionarEstabelecimentoScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuInicialClienteViewController implements Initializable {
	@FXML
	private Button btnAtualizarCadastro;

	@FXML
	private ImageView btnChat;

	@FXML
	private Button btnDelivery;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnHistorico;

	@FXML
	private Button btnPromocoes;

	@FXML
	private Button btnVisitar;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelCliente;

	@FXML
	private ListView<Estabelecimento> listEstabelecimento;

	private List<Estabelecimento> listObjEstabelecimentos = new ArrayList<>();
	private ObservableList<Estabelecimento> listObservable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String label;
		System.out.println("###########################################");
		System.out.println(MenuInicialClienteScreen.getCliente());
		if (MenuInicialClienteScreen.getCliente().getNome() != null) {
			label = MenuInicialClienteScreen.getCliente().getNome();
		} else {
			label = "Nome não Cadastrado";
		}

		labelCliente.setText(label);
		try {

			listObjEstabelecimentos = Requests.GETListEstabelecimentos("estabelecimentos");
			if (listObjEstabelecimentos.get(0) != null) {
				listObservable = FXCollections.observableArrayList(listObjEstabelecimentos);
				listEstabelecimento.setItems(listObservable);
			}
		} catch (Exception e) {
			e.getStackTrace();
			ErroJavafx.error("Erro!", "Erro ao carregar lista de Estabelecimentos!");
		}

		btnVisitar.setOnMouseClicked(event -> {
			Estabelecimento estabelecimento = listEstabelecimento.getSelectionModel().getSelectedItem();
			if (estabelecimento != null) {
				MenuInicialClienteScreen.setEstabelecimento(estabelecimento);
				SelecionarEstabelecimentoScreen selecionar = new SelecionarEstabelecimentoScreen();
				MenuInicialClienteScreen.getStage().close();
				try {
					selecionar.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				ErroJavafx.error("Estabelecimento não Selecionado",
						"Para iniciar, selecione um estabelecimento na lista e clique em visitar");
			}
		});

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

		btnVoltar.setOnMouseClicked(event -> {
			InicialJavafx inicial = new InicialJavafx();
			MenuInicialClienteScreen.setCliente(null);
			MenuInicialClienteScreen.getStage().close();
			try {
				inicial.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnChat.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnAtualizarCadastro.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnDelivery.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnHistorico.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnPromocoes.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

	}

}

package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.TimeZone;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.util.ConversorData;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.AdicionarBebidasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.AdicionarPratosEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoPedidoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.PedidoClienteScreen;
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

public class PedidoClienteViewController implements Initializable {
	@FXML
	private Button btnAdicionarBebida;

	@FXML
	private Button btnAdicionarPrato;

	@FXML
	private ImageView btnChat;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnFinalizar;

	@FXML
	private Button btnSolicitarAtendente;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelCliente;

	@FXML
	private Label labelListaBVazia;

	@FXML
	private Label labelListaPVazia;

	@FXML
	private Label labelPedido;

	@FXML
	private Label labelTotal;

	@FXML
	private ListView<Bebidas> listBebidas;

	@FXML
	private ListView<Pratos> listPratos;

	private ObservableList<Bebidas> listObservableBebidas;

	private ObservableList<Pratos> listObservablePratos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String label;
		if (MenuInicialClienteScreen.getCliente().getNome() != null) {
			label = MenuInicialClienteScreen.getCliente().getNome();
		} else {
			label = "Nome não Cadastrado";
		}
		labelCliente.setText(label);

		if (MenuInicialClienteScreen.getPedido().getListPratos() != null) {
			if (!MenuInicialClienteScreen.getPedido().getListPratos().isEmpty())
				listObservablePratos = FXCollections
						.observableArrayList(MenuInicialClienteScreen.getPedido().getListPratos());
			listPratos.setItems(listObservablePratos);
		} else {
			labelListaPVazia.setText("Sem consumo de Pratos");
		}

		if (MenuInicialClienteScreen.getPedido().getListBebidas() != null) {
			if (!MenuInicialClienteScreen.getPedido().getListBebidas().isEmpty())
				listObservableBebidas = FXCollections
						.observableArrayList(MenuInicialClienteScreen.getPedido().getListBebidas());
			listBebidas.setItems(listObservableBebidas);

		} else {

			labelListaBVazia.setText("Sem consumo de Bebidas");
		}

		if (MenuInicialClienteScreen.getPedido().getTotal() != null) {
			labelTotal.setText("Total - R$ " + String.format("%.2f", MenuInicialClienteScreen.getPedido().getTotal()));
		} else {
			labelTotal.setText("Total - R$ 0,00");
		}

		labelPedido.setText(MenuInicialClienteScreen.getEstabelecimento().getNome() + "\nPedido: "
				+ MenuInicialClienteScreen.getPedido().getIDPedido() + "\n"
				+ ConversorData.ConverterDataSp(MenuInicialClienteScreen.getPedido().getInstant()));

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

		btnVoltar.setOnMouseClicked(event -> {
			if (MenuInicialClienteScreen.getPedido().getTotal() == 0.00
					|| MenuInicialClienteScreen.getPedido().getTotal() == null) {
				ErroJavafx.information("Encerrando Pedido",
						"Como não houve consumo e o valor do pedido é igual a zero, o mesmo será encerrado automaticamente");
				SelecionarEstabelecimentoScreen selecionar = new SelecionarEstabelecimentoScreen();
				PedidoClienteScreen.getStage().close();

				try {
					selecionar.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				ErroJavafx.error("Pedido em Andamento",
						"Existe um pedido com consumo em andamento, para finalizar, efetue o encerramento do pedido e posterior pagamento!");
			}
		});

		btnChat.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnFinalizar.setOnMouseClicked(event -> {
			FormaPagamentoPedidoScreen pagamento = new FormaPagamentoPedidoScreen();
			try {
				pagamento.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnSolicitarAtendente.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnAdicionarBebida.setOnMouseClicked(event -> {
			if (MenuInicialClienteScreen.getEstabelecimento().getCardapio() != null) {
				if (MenuInicialClienteScreen.getEstabelecimento().getCardapio().getBebidas() != null) {
					AdicionarBebidasEstabelecimentoScreen adicionar = new AdicionarBebidasEstabelecimentoScreen();
					try {
						adicionar.start(new Stage());
					} catch (Exception e) {
						e.getStackTrace();
					}
				} else {
					ErroJavafx.error("Estabelecimento não possui bebidas cadastradas",
							"O Estabelecimento selecionado, não possui bebidas cadastradas!");
				}
			} else {
				ErroJavafx.error("Estabelecimento não possui bebidas cadastradas",
						"O Estabelecimento selecionado, não possui pratos cadastrados!");
			}
		});

		btnAdicionarPrato.setOnMouseClicked(event -> {
			if (MenuInicialClienteScreen.getEstabelecimento().getCardapio() != null) {
				if (MenuInicialClienteScreen.getEstabelecimento().getCardapio().getPratos() != null) {
					AdicionarPratosEstabelecimentoScreen adicionar = new AdicionarPratosEstabelecimentoScreen();
					try {
						adicionar.start(new Stage());
					} catch (Exception e) {
						e.getStackTrace();
					}
				} else {
					ErroJavafx.error("Estabelecimento não possui pratos cadastrados",
							"O Estabelecimento selecionado, não possui pratos cadastrados!");
				}
			} else {
				ErroJavafx.error("Estabelecimento não possui pratos cadastrados",
						"O Estabelecimento selecionado, não possui pratos cadastrados!");
			}
		});

	}

}

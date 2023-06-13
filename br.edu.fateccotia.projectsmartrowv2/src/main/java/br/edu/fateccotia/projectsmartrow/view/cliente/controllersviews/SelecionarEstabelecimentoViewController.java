package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.SelecionarEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.SelecionarMesasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.VisualizarBebidasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.VisualizarMesasEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.VisualizarPratosEstabelecimentoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SelecionarEstabelecimentoViewController implements Initializable {
	@FXML
	private ImageView btnChat;

	@FXML
	private Button btnCheckin;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnRetornar;

	@FXML
	private Button btnVisualizarMesas;

	@FXML
	private Button btnVisualizarPratos;

	@FXML
	private Button btnViualizarBebidas;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelBemVindo2;

	@FXML
	private Label labelCliente;

	@FXML
	private Label labelEstabelecimento;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (MenuInicialClienteScreen.getEstabelecimento() != null) {
			labelEstabelecimento.setText(MenuInicialClienteScreen.getEstabelecimento().exibirEstabelecimento());
		} else {
			labelEstabelecimento.setText("Estabelecimento é nulo");
		}

		if (MenuInicialClienteScreen.getCliente().getNome() != null) {
			labelCliente.setText(MenuInicialClienteScreen.getCliente().getNome());
		} else {
			labelCliente.setText("Nome não cadastrado!");
		}

		btnChat.setOnMouseClicked(event -> {
			ErroJavafx.funcaoNaoImplementada();
		});

		btnCheckin.setOnMouseClicked(event -> {
			List<Mesas> list = Requests.GETListMesas(
					"mesas/listarmesas/" + MenuInicialClienteScreen.getEstabelecimento().getIDEstabelecimento());
			if (!list.isEmpty()) {
				SelecionarMesasEstabelecimentoScreen selecionar = new SelecionarMesasEstabelecimentoScreen();
				try {
					selecionar.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				ErroJavafx.error("Mesas não disponíveis",
						"Não existem mesas disponíveis para CheckIn, por favor contate o estabelecimento");
			}
		});

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

		btnRetornar.setOnMouseClicked(event -> {
			MenuInicialClienteScreen inicial = new MenuInicialClienteScreen();
			SelecionarEstabelecimentoScreen.getStage().close();
			try {
				inicial.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnVisualizarMesas.setOnMouseClicked(event -> {
			List<Mesas> list = Requests.GETListMesas(
					"mesas/listarmesas/" + MenuInicialClienteScreen.getEstabelecimento().getIDEstabelecimento());

			if (!list.isEmpty()) {
				MenuInicialClienteScreen.getEstabelecimento().setMesas(list);
				VisualizarMesasEstabelecimentoScreen visualizar = new VisualizarMesasEstabelecimentoScreen();
				try {
					visualizar.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			} else {
				ErroJavafx.error("MesasNull", "Não existem mesas cadastradas no estabelecimento");
			}

		});

		btnVisualizarPratos.setOnMouseClicked(event -> {
			if (MenuInicialClienteScreen.getEstabelecimento().getCardapio() != null) {
				if (MenuInicialClienteScreen.getEstabelecimento().getCardapio().getPratos() != null) {
					VisualizarPratosEstabelecimentoScreen visualizar = new VisualizarPratosEstabelecimentoScreen();
					try {
						visualizar.start(new Stage());
					} catch (Exception e) {
						e.getStackTrace();
					}
				} else {
					ErroJavafx.error("PratosNull", "Não existem pratos cadastrados no estabelecimento");
				}
			} else {
				ErroJavafx.error("CardapioNull", "Não existe cardápio cadastrado no estabelecimento");
			}
		});

		btnViualizarBebidas.setOnMouseClicked(event -> {
			if (MenuInicialClienteScreen.getEstabelecimento().getCardapio() != null) {
				if (MenuInicialClienteScreen.getEstabelecimento().getCardapio().getBebidas() != null) {
					VisualizarBebidasEstabelecimentoScreen visualizar = new VisualizarBebidasEstabelecimentoScreen();
					try {
						visualizar.start(new Stage());
					} catch (Exception e) {
						e.getStackTrace();
					}
				} else {
					ErroJavafx.error("BebidasNull", "Não existem bebidas cadastradas no estabelecimento");
				}
			} else {
				ErroJavafx.error("CardapioNull", "Não existe cardápio cadastrado no estabelecimento");
			}
		});

		btnVoltar.setOnMouseClicked(event -> {
			MenuInicialClienteScreen inicial = new MenuInicialClienteScreen();
			SelecionarEstabelecimentoScreen.getStage().close();
			try {
				inicial.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

	}

}

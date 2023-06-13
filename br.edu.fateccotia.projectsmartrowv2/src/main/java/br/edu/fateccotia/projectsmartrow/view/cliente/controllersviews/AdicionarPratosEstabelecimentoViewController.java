package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.AdicionarPratosEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.PedidoClienteScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class AdicionarPratosEstabelecimentoViewController implements Initializable {

	@FXML
	private Button btnAdicionar;

	@FXML
	private Button btnRetornar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private ListView<Pratos> listPratos;

	ObservableList<Pratos> listObservable;

	List<Pratos> listObjPratos = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listObjPratos = MenuInicialClienteScreen.getEstabelecimento().getCardapio().getPratos();
		if (listObjPratos != null) {
			listObservable = FXCollections.observableArrayList(listObjPratos);
			listPratos.setItems(listObservable);
		}

		btnAdicionar.setOnMouseClicked(event -> {
			Pratos prato = listPratos.getSelectionModel().getSelectedItem();
			if (prato != null) {
				String request = "pedidos/adicionarprato?idpedido=" + MenuInicialClienteScreen.getPedido().getIDPedido()
						+ "&idprato=" + prato.getIDPrato();
				JSONObject obj = new JSONObject();
				Integer status = Requests.PUT(obj, request);
				if (status == 200) {
					ErroJavafx.information("Sucesso!",
							"O prato foi adicionado ao Pedido, em breve receberá em sua mesa! Obrigado!");
					MenuInicialClienteScreen.atualizarPedido();
					AdicionarPratosEstabelecimentoScreen.getStage().close();
					PedidoClienteScreen.getStage().close();
					PedidoClienteScreen pedidoClienteScrenn = new PedidoClienteScreen();
					try {
						pedidoClienteScrenn.start(new Stage());
					}
					catch (Exception e) {
						e.getStackTrace();
					}
					
				} else {
					ErroJavafx.error("Erro", "Ocorreu um erro ao solicitar o prato!");
				}
			} else {
				ErroJavafx.error("Prato não selecionado",
						"Para continuar selecione um prato ou retorne ao Menu anterior");
			}
		});

		btnRetornar.setOnMouseClicked(event -> {
			AdicionarPratosEstabelecimentoScreen.getStage().close();

		});

	}

}

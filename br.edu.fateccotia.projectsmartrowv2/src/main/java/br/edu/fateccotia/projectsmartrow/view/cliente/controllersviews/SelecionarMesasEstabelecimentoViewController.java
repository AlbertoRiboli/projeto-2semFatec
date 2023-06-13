package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.model.Pedidos;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.JsonInObject;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.PedidoClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.SelecionarEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.SelecionarMesasEstabelecimentoScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SelecionarMesasEstabelecimentoViewController implements Initializable {

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnRetornar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelCliente;

	@FXML
	private ListView<Mesas> listMesas;

	ObservableList<Mesas> listObservable;

	List<Mesas> listObjMesas = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listObjMesas = Requests.GETListMesas(
				"mesas/listarmesas/" + MenuInicialClienteScreen.getEstabelecimento().getIDEstabelecimento());
		if (listObjMesas != null) {
			listObservable = FXCollections.observableArrayList(listObjMesas);
			listMesas.setItems(listObservable);
		}

		btnConfirmar.setOnMouseClicked(event -> {
			Mesas mesa = listMesas.getSelectionModel().getSelectedItem();
			if (mesa != null) {
				SelecionarMesasEstabelecimentoScreen.getStage().close();
				SelecionarEstabelecimentoScreen.getStage().close();

				String request = "pedidos?idestab="
						+ MenuInicialClienteScreen.getEstabelecimento().getIDEstabelecimento() + "&idcliente="
						+ MenuInicialClienteScreen.getCliente().getIDCliente() + "&idmesa=" + mesa.getIDMesa();
				Pedidos pedido = JsonInObject.stringToPedidos(Requests.POSTPedido(request),
						MenuInicialClienteScreen.getCliente(), MenuInicialClienteScreen.getEstabelecimento());
				MenuInicialClienteScreen.setPedido(pedido);
				System.out.println("OK <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				
				PedidoClienteScreen pedidoClienteScrenn = new PedidoClienteScreen();
				try {
					pedidoClienteScrenn.start(new Stage());
				}
				catch (Exception e) {
					e.getStackTrace();
				}

			} else {
				ErroJavafx.error("Mesa não selecionada!", "Para continuar, primeiro selecione uma mesa");
			}
		});

		btnRetornar.setOnMouseClicked(event -> {
			SelecionarMesasEstabelecimentoScreen.getStage().close();

		});

	}

}

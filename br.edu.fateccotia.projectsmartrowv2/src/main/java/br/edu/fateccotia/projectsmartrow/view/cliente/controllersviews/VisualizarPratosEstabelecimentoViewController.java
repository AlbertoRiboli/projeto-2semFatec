package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.VisualizarPratosEstabelecimentoScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VisualizarPratosEstabelecimentoViewController implements Initializable {
	@FXML
	private Button btnRetornar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelCliente;

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

		btnRetornar.setOnMouseClicked(event -> {
			VisualizarPratosEstabelecimentoScreen.getStage().close();

		});

	}

}

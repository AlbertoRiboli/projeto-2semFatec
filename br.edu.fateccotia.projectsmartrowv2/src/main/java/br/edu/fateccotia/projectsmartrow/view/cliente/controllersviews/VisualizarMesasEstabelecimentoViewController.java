package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.VisualizarMesasEstabelecimentoScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VisualizarMesasEstabelecimentoViewController implements Initializable {
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

		listObjMesas = MenuInicialClienteScreen.getEstabelecimento().getMesas();
		if (listObjMesas != null) {
			listObservable = FXCollections.observableArrayList(listObjMesas);
			listMesas.setItems(listObservable);
		}

		btnRetornar.setOnMouseClicked(event -> {
			VisualizarMesasEstabelecimentoScreen.getStage().close();

		});

	}

}

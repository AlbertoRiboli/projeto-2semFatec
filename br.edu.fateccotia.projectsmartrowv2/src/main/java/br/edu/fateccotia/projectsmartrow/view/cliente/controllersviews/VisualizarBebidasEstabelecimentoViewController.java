package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.VisualizarBebidasEstabelecimentoScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class VisualizarBebidasEstabelecimentoViewController implements Initializable {

    @FXML
    private Button btnRetornar;

    @FXML
    private Label labelBemVindo;

    @FXML
    private Label labelCliente;

    @FXML
    private ListView<Bebidas> listBebidas;
    
	ObservableList<Bebidas> listObservable;

	List<Bebidas> listObjBebidas = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listObjBebidas = MenuInicialClienteScreen.getEstabelecimento().getCardapio().getBebidas();
		if (listObjBebidas != null) {
			listObservable = FXCollections.observableArrayList(listObjBebidas);
			listBebidas.setItems(listObservable);
		}

		btnRetornar.setOnMouseClicked(event -> {
			VisualizarBebidasEstabelecimentoScreen.getStage().close();

		});

	}

}

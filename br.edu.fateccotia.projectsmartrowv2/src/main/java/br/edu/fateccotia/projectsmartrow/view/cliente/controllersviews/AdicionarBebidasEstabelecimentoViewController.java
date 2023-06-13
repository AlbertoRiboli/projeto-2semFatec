package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.util.ErroJavafx;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.AdicionarBebidasEstabelecimentoScreen;
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

public class AdicionarBebidasEstabelecimentoViewController implements Initializable {


    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnRetornar;

    @FXML
    private Label labelBemVindo;

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

		btnAdicionar.setOnMouseClicked(event -> {
			Bebidas bebida = listBebidas.getSelectionModel().getSelectedItem();
			if (bebida != null) {
				String request = "pedidos/adicionarbebida?idpedido=" + MenuInicialClienteScreen.getPedido().getIDPedido()
						+ "&idbebida=" + bebida.getIDBebida();
				JSONObject obj = new JSONObject();
				Integer status = Requests.PUT(obj, request);
				if (status == 200) {
					ErroJavafx.information("Sucesso!",
							"A bebida foi adicionado ao Pedido, em breve receberá em sua mesa! Obrigado!");
					MenuInicialClienteScreen.atualizarPedido();
					AdicionarBebidasEstabelecimentoScreen.getStage().close();
					PedidoClienteScreen.getStage().close();
					PedidoClienteScreen pedidoClienteScrenn = new PedidoClienteScreen();
					try {
						pedidoClienteScrenn.start(new Stage());
					}
					catch (Exception e) {
						e.getStackTrace();
					}
					
				} else {
					ErroJavafx.error("Erro", "Ocorreu um erro ao solicitar a bebida!");
				}
			} else {
				ErroJavafx.error("Bebida não selecionada",
						"Para continuar selecione uma bebida ou retorne ao Menu anterior");
			}
		});

		btnRetornar.setOnMouseClicked(event -> {
			AdicionarBebidasEstabelecimentoScreen.getStage().close();

		});

	}

}

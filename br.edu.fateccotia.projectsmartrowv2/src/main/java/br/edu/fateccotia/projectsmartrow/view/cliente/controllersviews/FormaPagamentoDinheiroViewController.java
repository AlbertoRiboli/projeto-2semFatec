package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.view.cliente.screens.ConfirmacaoPagamentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoDinheiroScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoPedidoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.PedidoClienteScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FormaPagamentoDinheiroViewController implements Initializable {

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnRetornar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelPedido;

	@FXML
	private Label labelValor;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelPedido.setText("Pedido N. " + MenuInicialClienteScreen.getPedido().getIDPedido().toString());
		labelValor.setText("Valor Total: R$ " + String.format("%.2f", MenuInicialClienteScreen.getPedido().getTotal()));

		btnConfirmar.setOnMouseClicked(event -> {
			ConfirmacaoPagamentoScreen confirmacao = new ConfirmacaoPagamentoScreen();
			FormaPagamentoDinheiroScreen.getStage().close();
			PedidoClienteScreen.getStage().close();

			try {
				confirmacao.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnRetornar.setOnMouseClicked(event -> {
			FormaPagamentoPedidoScreen pedido = new FormaPagamentoPedidoScreen();
			FormaPagamentoDinheiroScreen.getStage().close();

			try {
				pedido.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

	}

}

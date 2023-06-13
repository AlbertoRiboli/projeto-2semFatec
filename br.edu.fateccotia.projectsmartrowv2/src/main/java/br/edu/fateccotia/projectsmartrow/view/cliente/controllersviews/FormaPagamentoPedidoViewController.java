package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoCartaoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoDinheiroScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoPedidoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoPixScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.PedidoClienteScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class FormaPagamentoPedidoViewController implements Initializable {

	@FXML
    private Button btnCartao;

    @FXML
    private Button btnDinheiro;

    @FXML
    private Button btnPix;

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
		
		labelPedido.setText(MenuInicialClienteScreen.getPedido().getIDPedido().toString());
		labelValor.setText("Valor Total: R$ " + String.format("%.2f", MenuInicialClienteScreen.getPedido().getTotal()));
		

		btnCartao.setOnMouseClicked(event -> {
			FormaPagamentoCartaoScreen cartao = new FormaPagamentoCartaoScreen();
			FormaPagamentoPedidoScreen.getStage().close();
			
			try {
				cartao.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});
		
		btnDinheiro.setOnMouseClicked(event -> {
			FormaPagamentoDinheiroScreen dinheiro = new FormaPagamentoDinheiroScreen();
			FormaPagamentoPedidoScreen.getStage().close();
			
			try {
				dinheiro.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});
		
		btnPix.setOnMouseClicked(event -> {
			FormaPagamentoPixScreen pix = new FormaPagamentoPixScreen();
			FormaPagamentoPedidoScreen.getStage().close();
			
			try {
				pix.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});
		
		btnRetornar.setOnMouseClicked(event -> {
			FormaPagamentoPedidoScreen.getStage().close();
			
		});
	}

}

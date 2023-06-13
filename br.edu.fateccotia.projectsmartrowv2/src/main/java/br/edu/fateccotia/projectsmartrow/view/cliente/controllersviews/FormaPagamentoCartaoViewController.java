package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.ConfirmacaoPagamentoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoCartaoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoPedidoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.FormaPagamentoPixScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.MenuInicialClienteScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.PedidoClienteScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormaPagamentoCartaoViewController implements Initializable {

	@FXML
	private Button btnConfirmar;

	@FXML
	private Button btnRetornar;

	@FXML
	private Label labelBemVindo;

	@FXML
	private Label labelPedido;

	@FXML
	private Label labelStatus;

	@FXML
	private Label labelValor;

	@FXML
	private TextField textCodigo;

	@FXML
	private TextField textNome;

	@FXML
	private TextField textNumero;

	@FXML
	private TextField textValidade;

	boolean numeroIsValid = false;
	boolean codigoIsValid = false;
	boolean nomeIsValid = false;
	boolean validadeIsValid = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		labelPedido.setText("Pedido N. " + MenuInicialClienteScreen.getPedido().getIDPedido().toString());
		labelValor.setText("Valor Total: R$ " + String.format("%.2f", MenuInicialClienteScreen.getPedido().getTotal()));

		btnConfirmar.setOnMouseClicked(event -> {
			if (ValidadorDadosEntrada.validaCartao(textNumero.getText()) == true) {
				numeroIsValid = true;
			} else {
				labelStatus.setText("Número de Cartão Inválido! O Campo deve ter 16 números");
			}

			if (ValidadorDadosEntrada.validaCodigo(textCodigo.getText()) == true) {
				codigoIsValid = true;
			} else {
				labelStatus.setText("Número de Código Inválido! O Campo deve ter 3 números");
			}

			if (ValidadorDadosEntrada.validaNome(textNome.getText()) == true) {
				nomeIsValid = true;
			} else {
				labelStatus.setText("Nome Inválido! O Campo deve ter o Nome do Cartão");
			}

			if (ValidadorDadosEntrada.validaValidade(textValidade.getText()) == true) {
				validadeIsValid = true;
			} else {
				labelStatus.setText("A válidade deve ter o formato MMAAAA, apenas números!");
			}

			if (numeroIsValid == true && codigoIsValid == true && nomeIsValid == true && validadeIsValid == true) {
				ConfirmacaoPagamentoScreen confirmacao = new ConfirmacaoPagamentoScreen();
				FormaPagamentoCartaoScreen.getStage().close();
				PedidoClienteScreen.getStage().close();

				try {
					confirmacao.start(new Stage());
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
		});
		
		btnRetornar.setOnMouseClicked(event -> {
			FormaPagamentoPedidoScreen pedido = new FormaPagamentoPedidoScreen();
			FormaPagamentoCartaoScreen.getStage().close();

			try {
				pedido.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});
		

	}

}

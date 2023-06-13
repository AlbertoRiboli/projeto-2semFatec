package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Mesas;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.AtualizarMesasScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MesasEstabelecimentoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AtualizarMesasViewController implements Initializable {

	@FXML
	private ImageView btnChat;

	@FXML
	private ImageView btnEncerrar;

	@FXML
	private Button btnProximo;

	@FXML
	private ImageView btnVoltar;

	@FXML
	private Label labelStatus;

	@FXML
	private TextField textCapacidade;

	@FXML
	private TextField textNumMesa;

	private static Mesas mesa;

	private boolean numMesaIsValid = false;
	private boolean capacidadeIsValid = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		textNumMesa.setText(String.valueOf(mesa.getNumMesa()));
		textCapacidade.setText(String.valueOf(mesa.getCapacidadePessoas()));

		btnProximo.setOnMouseClicked((MouseEvent e) -> {
			if (ValidadorDadosEntrada.validaNome(textNumMesa.getText()) == true) {
				mesa.setNumMesa(Integer.parseInt(textNumMesa.getText()));
				numMesaIsValid = true;

			} else {
				labelStatus.setText("A Mesa deve conter seu número");
			}
			if (ValidadorDadosEntrada.validaNome(textCapacidade.getText()) == true) {
				mesa.setCapacidadePessoas(Integer.parseInt(textCapacidade.getText()));
				capacidadeIsValid = true;
			} else {
				labelStatus.setText("A Mesa deve conter sua capacidade");
			}

			if (numMesaIsValid == true && capacidadeIsValid == true) {

				Integer status = atualizarMesa(mesa);

				if (status == 200) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Operação Realizada com Sucesso!");
					alert.setHeaderText("Operação Realizada com Sucesso!");
					alert.setContentText("A Mesa foi atualizada com sucesso!");
					alert.showAndWait();

					textNumMesa.setText("");
					textCapacidade.setText("");
					labelStatus.setText("");
					
					MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
					System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento());
					
					MesasEstabelecimentoScreen mesas = new MesasEstabelecimentoScreen();
					AtualizarMesasScreen.getStage().close();
					
					try {
						mesas.start(new Stage());
						
					}
					catch(Exception e1) {
						e1.getStackTrace();
					}

				} else {
					labelStatus.setText("Erro ao atualizar Bebida! Verifique os dados informados");
				}

			}

			else {
				labelStatus.setText("Erro ao atualizar Bebida! Verifique os dados informados");
			}

		});

		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});

		btnVoltar.setOnMouseClicked(event -> {
			MesasEstabelecimentoScreen mesasEstabelecimento = new MesasEstabelecimentoScreen();
			AtualizarMesasScreen.getStage().close();
			try {
				mesasEstabelecimento.start(new Stage());
			} catch (Exception e) {
				e.getStackTrace();
			}
		});

		btnChat.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoViewController.funcaoNaoImplementada();
		});

	}

	public Integer atualizarMesa(Mesas mesa) {
		Integer status = 0;
		String enderecoRequest = "mesas/" + mesa.getIDMesa();
		JSONObject obj = new JSONObject();
		obj.put("numMesa", mesa.getNumMesa());
		obj.put("capacidadePessoas", mesa.getCapacidadePessoas());

		status = Requests.PUT(obj, enderecoRequest);

		return status;

	}

	public static Mesas getMesa() {
		return mesa;
	}

	public static void setMesa(Mesas mesa) {
		AtualizarMesasViewController.mesa = mesa;
	}

}

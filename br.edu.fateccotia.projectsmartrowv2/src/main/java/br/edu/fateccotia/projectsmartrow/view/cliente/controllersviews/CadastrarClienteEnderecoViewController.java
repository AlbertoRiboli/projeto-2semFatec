package br.edu.fateccotia.projectsmartrow.view.cliente.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Endereco;
import br.edu.fateccotia.projectsmartrow.services.BuscaCepAPI;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteConfirmacaoScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteEnderecoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastrarClienteEnderecoViewController implements Initializable {

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnProximo;

	@FXML
	private Label labelStatus;

	@FXML
	private TextField textBairro;

	@FXML
	private TextField textCep;

	@FXML
	private TextField textCidade;

	@FXML
	private TextField textComplemento;

	@FXML
	private TextField textLogradouro;

	@FXML
	private TextField textNumero;

	@FXML
	private TextField textReferencia;

	@FXML
	private TextField textUf;

	@FXML
	private ImageView btEncerrar;

	private Endereco endereco;
	private boolean cepIsValid = false;
	
	public JSONObject enderecoToJson(Endereco endereco) {
		JSONObject obj = new JSONObject();
		
		return obj;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		btnBuscar.setOnMouseClicked(event -> {
			if (ValidadorDadosEntrada.validaCep(textCep.getText()) == true) {
				try {
					endereco = BuscaCepAPI.buscaCep(textCep.getText());
					System.out.println(endereco);
					if (endereco != null) {
						labelStatus.setText("");
						textLogradouro.setText(endereco.getLogradouro());
						textBairro.setText(endereco.getBairro());
						textCidade.setText(endereco.getLocalidade());
						textUf.setText(endereco.getUf());

						cepIsValid = true;
					} else {
						labelStatus.setText("cep invalido");
					}
				} catch (JSONException e) {
					System.out.println("A request retornou um JSONObject inválido" + e.getStackTrace());
					labelStatus.setText("cep invalido");
				}
			} else {
				labelStatus.setText("digite um cep válido");
			}

		});
		btnProximo.setOnMouseClicked(event -> {
			if (!textNumero.getText().isEmpty() && cepIsValid == true) {
				labelStatus.setText("");
				endereco.setNumero(textNumero.getText());
				endereco.setComplemento(textComplemento.getText());
				endereco.setReferencia(textReferencia.getText());
				
				String enderecoString = Requests.POSTEndereco("enderecos?cep=" + endereco.getCep() 
				+ "&num=" + endereco.getNumero() 
				+ "&comp=" + endereco.getComplemento()
				+ "&ref=" + endereco.getReferencia());
				
				System.out.println(enderecoString);
				
				JSONObject obj = new JSONObject(enderecoString);
				JSONObject atribEnd = new JSONObject();
				atribEnd.put("endereco", obj);
				
				Integer status = null;
				status = CadastrarClienteDadosScreen.cadastrarCliente(CadastrarClienteDadosScreen.getCliente(), obj);
				if (status == 200) {
					CadastrarClienteConfirmacaoScreen cadastrarConfirmacao = new CadastrarClienteConfirmacaoScreen();
					CadastrarClienteEnderecoScreen.getStage().close();
					try {
						cadastrarConfirmacao.start(new Stage());
					}catch(Exception e) {
						System.out.println(e.getStackTrace());
					}
				}
			}
			else {
				labelStatus.setText("complete as informações");
			}

		});
		btEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
	}

}

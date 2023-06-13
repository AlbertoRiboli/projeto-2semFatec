package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Endereco;
import br.edu.fateccotia.projectsmartrow.services.BuscaCepAPI;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoConfirmacaoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoEnderecoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CadastrarEstabelecimentoEnderecoViewController implements Initializable {

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
				
				String teste = Requests.POSTEndereco("enderecos?cep=" + endereco.getCep() 
				+ "&num=" + endereco.getNumero() 
				+ "&comp=" + endereco.getComplemento()
				+ "&ref=" + endereco.getReferencia());
				
				JSONObject obj = new JSONObject(teste);
				JSONObject atribEnd = new JSONObject();
				atribEnd.put("endereco", obj);
				
				Integer status = null;
				status = CadastrarEstabelecimentoDadosScreen.cadastrarEstabelecimento(CadastrarEstabelecimentoDadosScreen.getEstabelecimento(), obj);
				if (status == 200) {
					CadastrarEstabelecimentoConfirmacaoScreen cadastrarConfirmacao = new CadastrarEstabelecimentoConfirmacaoScreen();
					CadastrarEstabelecimentoEnderecoScreen.getStage().close();
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

package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Pratos;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarPratosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.PratosEstabelecimentoScreen;
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

public class CadastrarPratosViewController implements Initializable {

	@FXML
    private Button btnAdicionarImagem;

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
    private TextField textIngredientes;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textTipoPrato;

    @FXML
    private TextField textValor;



	private boolean nomeIsValid = false;
	private boolean ingredienteIsValid = false;
	private boolean tipoPratoIsValid = false;
	private boolean valorIsValid = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnProximo.setOnMouseClicked((MouseEvent e) -> {
			Pratos prato = new Pratos();
			if(ValidadorDadosEntrada.validaNome(textNome.getText()) == true) {
				prato.setNome(textNome.getText());
				nomeIsValid = true;
				
			}
			else {
				labelStatus.setText("O Prato deve conter nome");
			}
			if(ValidadorDadosEntrada.validaNome(textIngredientes.getText()) == true) {
				prato.setIngredientes(textIngredientes.getText());
				ingredienteIsValid = true;
			}
			else {
				labelStatus.setText("O Prato deve conter Ingredientes");
			}
			if(ValidadorDadosEntrada.validaNome(textTipoPrato.getText()) == true) {
				prato.setTipoPrato(textTipoPrato.getText());
				tipoPratoIsValid = true;
			}
			else {
				labelStatus.setText("O Prato deve conter o tipo do prato");
			}
			if(ValidadorDadosEntrada.validaValor(textValor.getText()) == true) {
				prato.setValor(Double.parseDouble(textValor.getText()));
				valorIsValid = true;
			}
			else {
				labelStatus.setText("Digite um valor válido");
			}
			
			if(nomeIsValid == true && ingredienteIsValid == true && tipoPratoIsValid == true && valorIsValid == true) {
				prato.setImagem("Ainda não Implementado");
				
				Integer status = cadastrarPrato(prato);
				
				if(status == 200) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Operação Realizada com Sucesso!");
					alert.setHeaderText("Operação Realizada com Sucesso!");
					alert.setContentText("O prato foi adicionado com sucesso ao cardapio!");
					alert.showAndWait();
					
					textNome.setText("");
					textIngredientes.setText("");
					textTipoPrato.setText("");
					textValor.setText("");
					labelStatus.setText("");
					
					MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
					System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento());
					
					
				}
				else {
					labelStatus.setText("Erro ao adicionar Prato!");
				}
				
			}
			
			
			
			
		});
		
		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
		
		btnVoltar.setOnMouseClicked(event -> {
			PratosEstabelecimentoScreen pratosEstabeleicmento = new PratosEstabelecimentoScreen();
			CadastrarPratosScreen.getStage().close();
			try {
				pratosEstabeleicmento.start(new Stage());
			}
			catch(Exception e) {
				e.getStackTrace();
			}
		});
		
		btnChat.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoViewController.funcaoNaoImplementada();
		});
		
		btnAdicionarImagem.setOnMouseClicked(event -> {
			MenuInicialEstabelecimentoViewController.funcaoNaoImplementada();
		});
	}
	
	public Integer cadastrarPrato(Pratos prato) {
		Integer status = 0;
		String enderecoRequest = "pratos/cadastrarprato/" + MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento();
		JSONObject obj = new JSONObject();
		obj.put("nome", prato.getNome());
		obj.put("tipoPrato", prato.getTipoPrato());
		obj.put("ingredientes", prato.getIngredientes());
		obj.put("valor", prato.getValor());
		obj.put("imagem", prato.getImagem());
		
		status = Requests.POST(obj, enderecoRequest);
					
		return status;
		
	}

}

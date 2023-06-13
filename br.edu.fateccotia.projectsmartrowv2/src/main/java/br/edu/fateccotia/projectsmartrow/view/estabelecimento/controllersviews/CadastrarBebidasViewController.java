package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONObject;

import br.edu.fateccotia.projectsmartrow.model.Bebidas;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarBebidasScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.MenuInicialEstabelecimentoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.BebidasEstabelecimentoScreen;
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

public class CadastrarBebidasViewController implements Initializable {

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
	    private TextField textNome;

	    @FXML
	    private TextField textTipoBebida;

	    @FXML
	    private TextField textValor;



	private boolean nomeIsValid = false;
	private boolean tipoBebidaIsValid = false;
	private boolean valorIsValid = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnProximo.setOnMouseClicked((MouseEvent e) -> {
			Bebidas bebida = new Bebidas();
			if(ValidadorDadosEntrada.validaNome(textNome.getText()) == true) {
				bebida.setNome(textNome.getText());
				nomeIsValid = true;
				
			}
			else {
				labelStatus.setText("A Bebida deve conter nome");
			}
			if(ValidadorDadosEntrada.validaNome(textTipoBebida.getText()) == true) {
				bebida.setTipoBebida(textTipoBebida.getText());
				tipoBebidaIsValid = true;
			}
			else {
				labelStatus.setText("A Bebida deve conter o tipo da bebida");
			}
			if(ValidadorDadosEntrada.validaValor(textValor.getText()) == true) {
				bebida.setValor(Double.parseDouble(textValor.getText()));
				valorIsValid = true;
			}
			else {
				labelStatus.setText("Digite um valor válido");
			}
			
			if(nomeIsValid == true && tipoBebidaIsValid == true && valorIsValid == true) {
				bebida.setImagem("Ainda não Implementado");
				
				Integer status = cadastrarBebida(bebida);
				
				if(status == 200) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Operação Realizada com Sucesso!");
					alert.setHeaderText("Operação Realizada com Sucesso!");
					alert.setContentText("A bebida foi adicionada com sucesso ao cardapio!");
					alert.showAndWait();
					
					textNome.setText("");
					textTipoBebida.setText("");
					textValor.setText("");
					labelStatus.setText("");
					
					MenuInicialEstabelecimentoViewController.atualizarEstabelecimentoServidor();
					System.out.println(MenuInicialEstabelecimentoScreen.getEstabelecimento());
					
					
				}
				else {
					labelStatus.setText("Erro ao adicionar Bebida!");
				}
				
			}
			
			
			
			
		});
		
		btnEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
		
		btnVoltar.setOnMouseClicked(event -> {
			BebidasEstabelecimentoScreen bebidasEstabelecimento = new BebidasEstabelecimentoScreen();
			CadastrarBebidasScreen.getStage().close();
			try {
				bebidasEstabelecimento.start(new Stage());
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
	
	public Integer cadastrarBebida(Bebidas bebida) {
		Integer status = 0;
		String enderecoRequest = "bebidas/cadastrarbebida/" + MenuInicialEstabelecimentoScreen.getEstabelecimento().getIDEstabelecimento();
		JSONObject obj = new JSONObject();
		obj.put("nome", bebida.getNome());
		obj.put("tipoBebida", bebida.getTipoBebida());
		obj.put("valor", bebida.getValor());
		obj.put("imagem", bebida.getImagem());
		
		status = Requests.POST(obj, enderecoRequest);
					
		return status;
		
	}

}

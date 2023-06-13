package br.edu.fateccotia.projectsmartrow.view.estabelecimento.controllersviews;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.fateccotia.projectsmartrow.model.Estabelecimento;
import br.edu.fateccotia.projectsmartrow.util.Requests;
import br.edu.fateccotia.projectsmartrow.util.ValidadorDadosEntrada;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.cliente.screens.CadastrarClienteEnderecoScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoDadosScreen;
import br.edu.fateccotia.projectsmartrow.view.estabelecimento.screens.CadastrarEstabelecimentoEnderecoScreen;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastrarEstabelecimentoDadosViewController implements Initializable {

	@FXML
    private ImageView btEncerrar;

    @FXML
    private Button btnProximo;

    @FXML
    private Label labelStatus;

    @FXML
    private TextField textCnpj;

    @FXML
    private TextField textHorario;

    @FXML
    private TextField textNome;

    @FXML
    private TextField textTelefone;

    @FXML
    private TextField textTelefone2;


	private boolean nomeIsValid = false;
	private boolean cnpjIsValid = false;
	private boolean telefoneIsValid = false;
	private boolean telefone2IsValid = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnProximo.setOnMouseClicked((MouseEvent e) -> {
			Estabelecimento estabelecimento = CadastrarEstabelecimentoDadosScreen.getEstabelecimento();

			if (ValidadorDadosEntrada.validaNome(textNome.getText()) == true) {
				System.out.println("VALIDOU NOME <<<<<<<<<<<<<<<<<<<<");
				nomeIsValid = true;
			} else {
				labelStatus.setText("nome inválido");
			}
			if (ValidadorDadosEntrada.validaCnpj(textCnpj.getText()) == true) {
				String buscarEstabelecimento = Requests.GET("estabelecimentos/cnpj/" + textCnpj.getText());
				if (buscarEstabelecimento == null) {
					System.out.println("VALIDOU CNPJ <<<<<<<<<<<<<<<<<<<<");
					cnpjIsValid = true;
				} else {
					labelStatus.setText("CNPJ já utilizado");
				}
			} else {
				labelStatus.setText("CNPJ inválido");
			}
			if (ValidadorDadosEntrada.validaTelefone(textTelefone.getText()) == true) {
				telefoneIsValid = true;
			} else {
				labelStatus.setText("Telefone Inválido");
			}
			if (ValidadorDadosEntrada.validaTelefone(textTelefone2.getText()) == true) {
				System.out.println("VALIDOU TELEFONE2 <<<<<<<<<<<<<<<<<<<<");
				telefone2IsValid = true;
			} else {
				labelStatus.setText("Telefone Inválido");
			}

			if (nomeIsValid == true && cnpjIsValid == true && telefoneIsValid == true && telefone2IsValid == true) {
				CadastrarEstabelecimentoDadosScreen.getEstabelecimento().setCnpj(textCnpj.getText());
				CadastrarEstabelecimentoDadosScreen.getEstabelecimento().setNome(textNome.getText());
				CadastrarEstabelecimentoDadosScreen.getEstabelecimento().setTelefone(textTelefone.getText());
				CadastrarEstabelecimentoDadosScreen.getEstabelecimento().setTelefone2(textTelefone2.getText());
				CadastrarEstabelecimentoDadosScreen.getEstabelecimento().setHorarioFuncionamento(textHorario.getText());
				
				System.out.println("OBJETO CRIADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				
				CadastrarEstabelecimentoEnderecoScreen cadastrarEndereco = new CadastrarEstabelecimentoEnderecoScreen();
				
				System.out.println("OBJETO CADASTRADO <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				CadastrarEstabelecimentoDadosScreen.getStage().close();
				try {
					cadastrarEndereco.start(new Stage());
				} catch (Exception e1) {
					e1.getStackTrace();
				}

			}

		});

		btEncerrar.setOnMouseClicked(event -> {
			System.exit(0);
		});
	}

}
